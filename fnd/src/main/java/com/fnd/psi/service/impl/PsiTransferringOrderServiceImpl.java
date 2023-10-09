package com.fnd.psi.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.constant.StorageStatusEnum;
import com.fnd.psi.dto.*;
import com.fnd.psi.dto.storage.PsiStorageOrderDTO;
import com.fnd.psi.dto.user.PsiUserDTO;
import com.fnd.psi.mapper.PsiTransferringOrderMapper;
import com.fnd.psi.model.PsiProductSku;
import com.fnd.psi.model.PsiTransferringOrder;
import com.fnd.psi.model.PsiUser;
import com.fnd.psi.model.WarehouseUserRelation;
import com.fnd.psi.security.FndSecurityContextUtil;
import com.fnd.psi.service.*;
import com.fnd.psi.utils.*;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 15:47
 * @Desc:
 * @See:
 */
@Slf4j
@Service("psiTransferringOrderService")
@AllArgsConstructor
public class PsiTransferringOrderServiceImpl extends ServiceImpl<PsiTransferringOrderMapper, PsiTransferringOrder> implements PsiTransferringOrderService {

    private ResultUtils resultUtils;
    private PsiStorageOrderService psiStorageOrderService;
    private UserService userService;
    private WarehouseUserRelationService warehouseUserRelationService;
    private PsiProductSkuService psiProductSkuService;
    private WarehouseInfoService warehouseInfoService;
    private PsiTransferringOrderStatusService psiTransferringOrderStatusService;

    @Override
    public ResultVo<PageDTO<PsiTransferringOrderDTO>> listPage(PsiTransferringOrderQuery psiTransferringOrderQuery) {
        PageDTO<PsiTransferringOrderDTO> resultPage= CopyBeanUtils.convert(psiTransferringOrderQuery, PageDTO.class);
        Page<PsiTransferringOrder> page = PSIBaseUtils.buildPageByQuery(psiTransferringOrderQuery);

        final PsiUserDTO user = FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser();

        LambdaQueryWrapper<PsiTransferringOrder> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(StrUtil.isNotBlank(psiTransferringOrderQuery.getTransferCode()), PsiTransferringOrder::getTransferCode, psiTransferringOrderQuery.getTransferCode());
        queryWrapper.like(StrUtil.isNotBlank(psiTransferringOrderQuery.getProductSkuCode()), PsiTransferringOrder::getProductSkuCode, psiTransferringOrderQuery.getProductSkuCode());
        queryWrapper.like(StrUtil.isNotBlank(psiTransferringOrderQuery.getProductSkuName()), PsiTransferringOrder::getProductSkuName, psiTransferringOrderQuery.getProductSkuName());
        queryWrapper.eq(ObjectUtil.isNotNull(psiTransferringOrderQuery.getTransferringStatus()), PsiTransferringOrder::getTransferringStatus, psiTransferringOrderQuery.getTransferringStatus());
        queryWrapper.eq(ObjectUtil.isNotNull(psiTransferringOrderQuery.getRelationStorageStatus()), PsiTransferringOrder::getRelationStorageStatus, psiTransferringOrderQuery.getRelationStorageStatus());
        queryWrapper.eq(ObjectUtil.isNotNull(psiTransferringOrderQuery.getWarehouseId()), PsiTransferringOrder::getSourceWarehouseId, psiTransferringOrderQuery.getWarehouseId());
        queryWrapper.eq(ObjectUtil.isNotNull(psiTransferringOrderQuery.getProductSkuId()), PsiTransferringOrder::getProductSkuId, psiTransferringOrderQuery.getProductSkuId());
        queryWrapper.eq(PsiTransferringOrder::getIsDeleted, 0);
        queryWrapper.orderByDesc(PsiTransferringOrder::getGmtCreate);

        List<Long> warehouseIdList = CollUtil.newArrayList();
        if (!user.getLevel().equals(0)) {
            List<WarehouseUserRelation> warehouseIdByUserIds = warehouseUserRelationService.getWarehouseIdByUserIds(Sets.newHashSet(user.getId()));
            warehouseIdList = warehouseIdByUserIds.stream().map(WarehouseUserRelation::getWarehouseId).collect(Collectors.toList());
            if (CollUtil.isEmpty(warehouseIdList)) {
                return resultUtils.returnSuccess(resultPage);
            }
            queryWrapper.in(PsiTransferringOrder::getSourceWarehouseId, warehouseIdList);
        }

        Page<PsiTransferringOrder> selectPage = baseMapper.selectPage(page, queryWrapper);

        resultPage= CopyBeanUtils.convert(selectPage, PageDTO.class);
        resultPage.setRecords(CopyBeanUtils.copyList(selectPage.getRecords(), PsiTransferringOrderDTO.class));
        resultPage.setPages(selectPage.getPages());

        Set<Long> userIds = CollUtil.newHashSet();
        Set<Long> ids = CollUtil.newHashSet();
        resultPage.getRecords().forEach(x -> {
            userIds.add(x.getCreateBy());
            userIds.add(x.getUpdateBy());
            ids.add(x.getId());
        });

        Map<Long, String> userMap = userService.queryByUserIds(userIds)
                .stream().collect(Collectors.toMap(PsiUser::getId, PsiUser::getUserName));

        Map<Long, List<PsiTransferringOrderUpdateStatusDetailDTO>> transferringOrderUpdateStatusMap = psiTransferringOrderStatusService.queryByTransferringId(ids)
                .stream().collect(Collectors.groupingBy(PsiTransferringOrderUpdateStatusDetailDTO::getTransferringOrderId));

        resultPage.getRecords().forEach(x -> {
            x.setCreateByName(userMap.get(x.getCreateBy()));
            x.setUpdateByName(userMap.get(x.getUpdateBy()));
            x.setSourceWarehouseName(warehouseInfoService.getWarehouseNameById(x.getSourceWarehouseId()));
            x.setTransferringStatusList(transferringOrderUpdateStatusMap.get(x.getId()));
        });

        return resultUtils.returnSuccess(resultPage);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultVo<PsiTransferringOrderDTO> save(PsiTransferringOrderDTO psiTransferringOrderDTO) {
        PsiUserDTO user = FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser();
        buildPsiTransferringOrderDTO(psiTransferringOrderDTO, user);

        PsiTransferringOrder psiTransferringOrder = CopyBeanUtils.convert(psiTransferringOrderDTO, PsiTransferringOrder.class);
        psiTransferringOrder.setTransferCode(PSICodeUtils.getTransferringOrderCode());
        this.save(psiTransferringOrder);

        psiTransferringOrderStatusService.saveTransferringOrderStatus(psiTransferringOrder);

        return resultUtils.returnSuccess(psiTransferringOrder);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultVo transferring(PsiTransferringOrderUpdateDTO psiTransferringOrderUpdateDTO) {
        final PsiTransferringOrder psiTransferringOrder = this.getById(psiTransferringOrderUpdateDTO.getId());
        if (psiTransferringOrder == null) {
            return ResultVoUtil.error("不存在调拨单");
        }
        if (IntegerUtils.isGreater(psiTransferringOrderUpdateDTO.getProductCount(), psiTransferringOrder.getNotYetStorageCount())) {
            return ResultVoUtil.error("调拨数量大于待入库的数量， 请重新检查！");
        }

        psiTransferringOrder.setReceivedStorageCount(IntegerUtils.add(psiTransferringOrder.getReceivedStorageCount(), psiTransferringOrderUpdateDTO.getProductCount()));
        psiTransferringOrder.setNotYetStorageCount(IntegerUtils.sub(psiTransferringOrder.getNotYetStorageCount(), psiTransferringOrderUpdateDTO.getProductCount()));
        psiTransferringOrder.setRelationStorageStatus(StorageStatusEnum.PARTIAL_WAREHOUSING.getCode());
        if (IntegerUtils.isEquality(psiTransferringOrder.getReceivedStorageCount(), psiTransferringOrder.getProductCount())) {
            psiTransferringOrder.setRelationStorageStatus(StorageStatusEnum.ALL_WAREHOUSING.getCode());
        }
        psiTransferringOrder.setUpdateBy(FndSecurityContextUtil.getContext().getId());
        psiTransferringOrder.setGmtModified(new Date());
        psiTransferringOrder.setRemarks(psiTransferringOrderUpdateDTO.getRemarks());
        this.updateById(psiTransferringOrder);

        //生成入库单
        PsiStorageOrderDTO psiStorageOrderDTO = buildStorageOrderDTO(psiTransferringOrderUpdateDTO);
        log.info("生成入库单， psiStorageOrderDTO： {}", JSONUtil.toJsonStr(psiStorageOrderDTO));
        psiStorageOrderService.createStorageOrder(psiStorageOrderDTO);

        return  resultUtils.returnSuccess(psiStorageOrderDTO);
    }

    @Override
    public ResultVo<PsiTransferringOrderDTO> transferringForWarehouse(PsiTransferringOrderUpdateDTO psiTransferringOrderUpdateDTO) {
        //生成入库单
        PsiStorageOrderDTO psiStorageOrderDTO = buildStorageOrderDTO(psiTransferringOrderUpdateDTO);
        log.info("生成入库单， psiStorageOrderDTO： {}", JSONUtil.toJsonStr(psiStorageOrderDTO));
        psiStorageOrderService.createStorageOrderForwarehouse(psiStorageOrderDTO);

        return  resultUtils.returnSuccess(psiStorageOrderDTO);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultVo<PsiTransferringOrderDTO> updateStatus(PsiTransferringOrderUpdateStatusDTO psiTransferringOrderUpdateStatusDTO) {
        final PsiTransferringOrder psiTransferringOrder = this.getById(psiTransferringOrderUpdateStatusDTO.getId());
        if (psiTransferringOrder == null) {
            return ResultVoUtil.error("不存在调拨单");
        }

//        psiTransferringOrder.setTransferringStatus(psiTransferringOrderUpdateStatusDTO.getTransferringStatus());
        psiTransferringOrder.setRemarks(psiTransferringOrderUpdateStatusDTO.getRemarks());
        psiTransferringOrder.setUpdateBy(FndSecurityContextUtil.getContext().getId());
        psiTransferringOrder.setGmtModified(new Date());

        this.updateById(psiTransferringOrder);

        //流程处理
        psiTransferringOrderUpdateStatusDTO.setUpdateBy(psiTransferringOrder.getUpdateBy());
        psiTransferringOrderStatusService.handleTransferringOrderStatus(psiTransferringOrderUpdateStatusDTO);

        return resultUtils.returnSuccess(CopyBeanUtils.convert(psiTransferringOrder, PsiTransferringOrderDTO.class));
    }

    private void buildPsiTransferringOrderDTO(PsiTransferringOrderDTO psiTransferringOrderDTO, PsiUserDTO user) {
        psiTransferringOrderDTO.setRelationStorageStatus(StorageStatusEnum.NOT_WAREHOUSED.getCode());
        psiTransferringOrderDTO.setReceivedStorageCount(0);
        psiTransferringOrderDTO.setNotYetStorageCount(psiTransferringOrderDTO.getProductCount());
        psiTransferringOrderDTO.setBelongUserId(user.getBelongUserId());
        psiTransferringOrderDTO.setCreateBy(user.getId());
        psiTransferringOrderDTO.setUpdateBy(user.getId());
    }


    private PsiStorageOrderDTO buildStorageOrderDTO( PsiTransferringOrderUpdateDTO psiTransferringOrderUpdateDTO) {
        /*PsiStorageOrderDTO psiStorageOrderDTO = new PsiStorageOrderDTO();
        psiStorageOrderDTO.setWarehouseId(psiTransferringOrderUpdateDTO.getTargetWarehouseId());
        psiStorageOrderDTO.setSourceId(psiTransferringOrder.getId());
        psiStorageOrderDTO.setSourceCode(psiTransferringOrder.getTransferCode());
        psiStorageOrderDTO.setSourceBusinessTime(psiTransferringOrder.getGmtModified());
        psiStorageOrderDTO.setProductSkuId(psiTransferringOrder.getProductSkuId());
        psiStorageOrderDTO.setProductSkuCode(psiTransferringOrder.getProductSkuCode());
        psiStorageOrderDTO.setProductCount(psiTransferringOrderUpdateDTO.getProductCount());
        psiStorageOrderDTO.setReceivedCount(psiTransferringOrderUpdateDTO.getProductCount());
        psiStorageOrderDTO.setStorageStatus(StorageStatusEnum.ALL_WAREHOUSING.getCode());
        psiStorageOrderDTO.setBelongUserId(psiTransferringOrder.getBelongUserId());
        psiStorageOrderDTO.setCreateBy(psiTransferringOrder.getUpdateBy());
        psiStorageOrderDTO.setUpdateBy(psiTransferringOrder.getUpdateBy());*/


        PsiUserDTO user = FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser();

        PsiProductSku psiProductSku = psiProductSkuService.getById(psiTransferringOrderUpdateDTO.getSkuId());

        PsiStorageOrderDTO psiStorageOrderDTO = new PsiStorageOrderDTO();
        psiStorageOrderDTO.setSourceWarehouseId(psiTransferringOrderUpdateDTO.getSourceWarehouseId());
        psiStorageOrderDTO.setWarehouseId(psiTransferringOrderUpdateDTO.getTargetWarehouseId());
        psiStorageOrderDTO.setSourceId(0L);
        psiStorageOrderDTO.setSourceCode(null);
        psiStorageOrderDTO.setSourceBusinessTime(new Date());
        psiStorageOrderDTO.setProductSkuId(psiTransferringOrderUpdateDTO.getSkuId());
        psiStorageOrderDTO.setProductSkuCode(psiProductSku.getSkuCode());
        psiStorageOrderDTO.setProductCount(psiTransferringOrderUpdateDTO.getProductCount());
        psiStorageOrderDTO.setReceivedCount(psiTransferringOrderUpdateDTO.getProductCount());
        psiStorageOrderDTO.setStorageStatus(StorageStatusEnum.ALL_WAREHOUSING.getCode());
        psiStorageOrderDTO.setAddType(psiTransferringOrderUpdateDTO.getAddType());
        psiStorageOrderDTO.setBelongUserId(user.getBelongUserId());
        psiStorageOrderDTO.setRemarks(psiTransferringOrderUpdateDTO.getRemarks());
        psiStorageOrderDTO.setCreateBy(user.getId());
        psiStorageOrderDTO.setUpdateBy(user.getId());

        return psiStorageOrderDTO;
    }
}
