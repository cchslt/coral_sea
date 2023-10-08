package com.fnd.psi.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.constant.UserTypeEnum;
import com.fnd.psi.dto.PageDTO;
import com.fnd.psi.dto.inventory.PsiInventoryDTO;
import com.fnd.psi.dto.storage.PsiStorageOrderDTO;
import com.fnd.psi.dto.user.PsiUserDTO;
import com.fnd.psi.dto.vo.PsiProductSkuTransferFlowRequestVO;
import com.fnd.psi.dto.vo.PsiProductSkuTransferFlowVO;
import com.fnd.psi.mapper.PsiStorageOrderMapper;
import com.fnd.psi.model.*;
import com.fnd.psi.security.FndSecurityContextUtil;
import com.fnd.psi.service.*;
import com.fnd.psi.utils.CopyBeanUtils;
import com.fnd.psi.utils.PSIBaseUtils;
import com.fnd.psi.utils.PSICodeUtils;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    private WarehouseInfoService warehouseInfoService;
    private WarehouseUserRelationService warehouseUserRelationService;

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
        PageDTO<PsiProductSkuTransferFlowVO> resultPage= CopyBeanUtils.convert(psiProductSkuTransferFlowVO, PageDTO.class);

        Page<PsiStorageOrder> page = PSIBaseUtils.buildPageByQuery(psiProductSkuTransferFlowVO);

        LambdaQueryWrapper<PsiStorageOrder> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(PsiStorageOrder::getProductSkuCode, psiProductSkuTransferFlowVO.getSkuCode());
        queryWrapper.eq(PsiStorageOrder::getIsDeleted, 0);

        final PsiUserDTO currentUser = FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser();
        if (!UserTypeEnum.PLATFORM_MANAGEMENT.getCode().equals(currentUser.getUserType())) {
            List<WarehouseUserRelation> warehouseIdByUserIds = warehouseUserRelationService.getWarehouseIdByUserIds(Sets.newHashSet(currentUser.getId()));
            List<Long> warehouseIdList = warehouseIdByUserIds.stream().map(WarehouseUserRelation::getWarehouseId).collect(Collectors.toList());
            if (CollUtil.isEmpty(warehouseIdList)) {
                return resultPage;
            }
            queryWrapper.and(item ->
                    item.in(PsiStorageOrder::getSourceWarehouseId, warehouseIdList)
                            .or().like(PsiStorageOrder::getWarehouseId, warehouseIdList)
            );
        }

        Page<PsiStorageOrder> selectPage = baseMapper.selectPage(page, queryWrapper);

        resultPage= CopyBeanUtils.convert(selectPage, PageDTO.class);
        resultPage.setRecords(CopyBeanUtils.copyList(selectPage.getRecords(), PsiProductSkuTransferFlowVO.class));
        resultPage.setPages(selectPage.getPages());

        Map<Long, String> userMap = CollUtil.newHashMap();
        Map<Long, String> skuMap = CollUtil.newHashMap();

        if (CollUtil.isNotEmpty(resultPage.getRecords())) {
            Set<Long> userIds = CollUtil.newHashSet();
            selectPage.getRecords().forEach(x -> userIds.add(x.getCreateBy()));

            userMap = userService.queryByUserIds(userIds)
                    .stream().collect(Collectors.toMap(PsiUser::getId, PsiUser::getUserName));
            skuMap = psiProductSkuService.findBySkuIdList(resultPage.getRecords().stream().map(PsiProductSkuTransferFlowVO::getProductSkuId).collect(Collectors.toList()))
                    .stream().collect(Collectors.toMap(PsiProductSku::getId, PsiProductSku::getSkuProductName));

        }

        for(PsiProductSkuTransferFlowVO x : resultPage.getRecords()) {
            x.setTransferDate(x.getGmtCreate());
            x.setCreateUserName(userMap.get(x.getCreateBy()));
            x.setTargetWarehouseName(warehouseInfoService.getWarehouseNameById(x.getWarehouseId()));
            x.setSourceWarehouseName(warehouseInfoService.getWarehouseNameById(x.getSourceWarehouseId()));
            x.setProductSkuName(skuMap.get(x.getProductSkuId()));
        }
        return resultPage;
    }
}
