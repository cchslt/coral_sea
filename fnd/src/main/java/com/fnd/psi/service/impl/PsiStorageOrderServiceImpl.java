package com.fnd.psi.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.dto.PageDTO;
import com.fnd.psi.dto.inventory.PsiInventoryDTO;
import com.fnd.psi.dto.storage.PsiStorageOrderDTO;
import com.fnd.psi.dto.vo.PsiProductSkuTransferFlowRequestVO;
import com.fnd.psi.dto.vo.PsiProductSkuTransferFlowVO;
import com.fnd.psi.mapper.PsiStorageOrderMapper;
import com.fnd.psi.model.PsiInventory;
import com.fnd.psi.model.PsiProductSku;
import com.fnd.psi.model.PsiStorageOrder;
import com.fnd.psi.model.PsiUser;
import com.fnd.psi.service.PsiInventoryService;
import com.fnd.psi.service.PsiProductSkuService;
import com.fnd.psi.service.PsiStorageOrderService;
import com.fnd.psi.service.UserService;
import com.fnd.psi.utils.CopyBeanUtils;
import com.fnd.psi.utils.PSIBaseUtils;
import com.fnd.psi.utils.PSICodeUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 17:30
 * @Desc:
 * @See:
 */
@Slf4j
@Service("psiStorageOrderService")
@AllArgsConstructor
public class PsiStorageOrderServiceImpl extends ServiceImpl<PsiStorageOrderMapper, PsiStorageOrder> implements PsiStorageOrderService {

    private PsiInventoryService psiInventoryService;
    private UserService userService;
    private PsiProductSkuService psiProductSkuService;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public PsiStorageOrder createStorageOrder(PsiStorageOrderDTO psiStorageOrderDTO) {
        PsiStorageOrder psiStorageOrder = CopyBeanUtils.convert(psiStorageOrderDTO, PsiStorageOrder.class);
        psiStorageOrder.setStorageOrderCode(PSICodeUtils.getStorageOrderCode());
        this.save(psiStorageOrder);

        // 操作库存
        PsiInventoryDTO psiInventoryDTO = buildPsiInventoryDTO(psiStorageOrder);
        //调拨先少调出仓，再增加调入仓
        if (psiStorageOrder.getAddType().equals(1)) {
//            psiInventoryService.subOrUpdate(psiInventoryDTO, psiStorageOrder.getStorageOrderCode());

            psiInventoryDTO.setWarehouseId(psiStorageOrder.getWarehouseId());
            psiInventoryService.addOrUpdate(psiInventoryDTO, psiStorageOrder.getStorageOrderCode());
        }
        //销售直接减掉调出仓
        if (psiStorageOrder.getAddType().equals(2)) {
            psiInventoryService.subOrUpdate(psiInventoryDTO, psiStorageOrder.getStorageOrderCode());
        }

        return psiStorageOrder;
    }

    /**
     * 不想写了，谁想优化就优化，别骂人
     * @param psiStorageOrderDTO
     * @return
     */
    @Override
    public PsiStorageOrder createStorageOrderForwarehouse(PsiStorageOrderDTO psiStorageOrderDTO) {
        PsiStorageOrder psiStorageOrder = CopyBeanUtils.convert(psiStorageOrderDTO, PsiStorageOrder.class);
        psiStorageOrder.setStorageOrderCode(PSICodeUtils.getStorageOrderCode());
        this.save(psiStorageOrder);

        // 操作库存
        PsiInventoryDTO psiInventoryDTO = buildPsiInventoryDTO(psiStorageOrder);
        //调拨先少调出仓，再增加调入仓
        if (psiStorageOrder.getAddType().equals(1)) {
            psiInventoryService.subOrUpdate(psiInventoryDTO, psiStorageOrder.getStorageOrderCode());

            psiInventoryDTO.setWarehouseId(psiStorageOrder.getWarehouseId());
            psiInventoryService.addOrUpdate(psiInventoryDTO, psiStorageOrder.getStorageOrderCode());
        }
        //销售直接减掉调出仓
        if (psiStorageOrder.getAddType().equals(2)) {
            psiInventoryService.subOrUpdate(psiInventoryDTO, psiStorageOrder.getStorageOrderCode());
        }

        return psiStorageOrder;
    }

    private PsiInventoryDTO buildPsiInventoryDTO(PsiStorageOrder psiStorageOrder) {
        PsiInventoryDTO psiInventoryDTO = new PsiInventoryDTO();
        psiInventoryDTO.setWarehouseId(psiStorageOrder.getSourceWarehouseId());
        psiInventoryDTO.setProductSkuId(psiStorageOrder.getProductSkuId());
        psiInventoryDTO.setProductSkuCode(psiStorageOrder.getProductSkuCode());
        psiInventoryDTO.setAvailableQuantity(psiStorageOrder.getProductCount());
        psiInventoryDTO.setSellableQuantity(psiStorageOrder.getProductCount());
        psiInventoryDTO.setCreateBy(psiStorageOrder.getCreateBy());
        psiInventoryDTO.setUpdateBy(psiStorageOrder.getUpdateBy());

        return psiInventoryDTO;

    }

    @Override
    public PageDTO<PsiProductSkuTransferFlowVO> transferringFlow(PsiProductSkuTransferFlowRequestVO psiProductSkuTransferFlowVO) {
        Page<PsiStorageOrder> page = PSIBaseUtils.buildPageByQuery(psiProductSkuTransferFlowVO);

        LambdaQueryWrapper<PsiStorageOrder> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(PsiStorageOrder::getProductSkuCode, psiProductSkuTransferFlowVO.getSkuCode());
        queryWrapper.eq(PsiStorageOrder::getIsDeleted, 0);

        Page<PsiStorageOrder> selectPage = baseMapper.selectPage(page, queryWrapper);

        PageDTO<PsiProductSkuTransferFlowVO> resultPage= CopyBeanUtils.convert(selectPage, PageDTO.class);
        resultPage.setRecords(CopyBeanUtils.copyList(selectPage.getRecords(), PsiProductSkuTransferFlowVO.class));
        resultPage.setPages(selectPage.getPages());

        Set<Long> userIds = CollUtil.newHashSet();
        selectPage.getRecords().forEach(x -> userIds.add(x.getCreateBy()));

        Map<Long, String> userMap = userService.queryByUserIds(userIds)
                .stream().collect(Collectors.toMap(PsiUser::getId, PsiUser::getUserName));

        Map<String, String> skuMap = psiProductSkuService.findBySkuCodeList(resultPage.getRecords().stream().map(PsiProductSkuTransferFlowVO::getSkuCode).collect(Collectors.toList()))
                .stream().collect(Collectors.toMap(PsiProductSku::getSkuCode, PsiProductSku::getSkuProductName));

        resultPage.getRecords().forEach(x -> {
            x.setCreateUserName(userMap.get(x.getCreateBy()));
            x.setTargetWarehouseName("xxxx-d");
            x.setSourceWarehouseName("xxxx-11");
            x.setProductSkuName(skuMap.get(x.getSkuCode()));
        });
        return resultPage;
    }
}
