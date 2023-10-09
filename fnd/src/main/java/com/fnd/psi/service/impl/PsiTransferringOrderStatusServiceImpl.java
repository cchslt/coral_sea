package com.fnd.psi.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.constant.TransferChangeEnum;
import com.fnd.psi.dto.PsiTransferringOrderUpdateStatusDTO;
import com.fnd.psi.dto.PsiTransferringOrderUpdateStatusDetailDTO;
import com.fnd.psi.mapper.PsiTransferringOrderStatusMapper;
import com.fnd.psi.model.PsiTransferringOrder;
import com.fnd.psi.model.PsiTransferringOrderStatus;
import com.fnd.psi.service.PsiTransferringOrderStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: chenchaohai
 * @Date: 2023-10-09 14:02
 * @Desc:
 * @See:
 */
@Slf4j
@Service("psiTransferringOrderStatusService")
public class PsiTransferringOrderStatusServiceImpl extends ServiceImpl<PsiTransferringOrderStatusMapper, PsiTransferringOrderStatus> implements PsiTransferringOrderStatusService {


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<PsiTransferringOrderStatus> handleTransferringOrderStatus(PsiTransferringOrderUpdateStatusDTO psiTransferringOrderUpdateStatusDTO) {
        LambdaQueryWrapper<PsiTransferringOrderStatus> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PsiTransferringOrderStatus::getTransferringOrderId, psiTransferringOrderUpdateStatusDTO.getId());
        queryWrapper.eq(PsiTransferringOrderStatus::getIsDeleted, 0);

        List<PsiTransferringOrderStatus> transferringOrderStatusList = this.list(queryWrapper);
        Map<Integer, PsiTransferringOrderStatus> transferringOrderStatusMap = transferringOrderStatusList.stream().collect(Collectors.toMap(PsiTransferringOrderStatus::getTransferringStatus, Function.identity(), (k1, k2) -> k1));

        List<PsiTransferringOrderUpdateStatusDetailDTO> transferringStatusList = psiTransferringOrderUpdateStatusDTO.getTransferringStatusList();

        //保存或更新
        List<PsiTransferringOrderStatus> resultList = new ArrayList<>();
        for (PsiTransferringOrderUpdateStatusDetailDTO detailDTO : transferringStatusList) {

            PsiTransferringOrderStatus psiTransferringOrderStatus = new PsiTransferringOrderStatus();
            psiTransferringOrderStatus.setCreateBy(psiTransferringOrderUpdateStatusDTO.getUpdateBy());
            if (transferringOrderStatusMap.containsKey(detailDTO.getTransferringStatus())) {
                PsiTransferringOrderStatus status = transferringOrderStatusMap.get(detailDTO.getTransferringStatus());
                psiTransferringOrderStatus.setId(status.getId());
                psiTransferringOrderStatus.setCreateBy(status.getCreateBy());
                psiTransferringOrderStatus.setGmtCreate(status.getGmtCreate());
            }
            psiTransferringOrderStatus.setTransferringOrderId(psiTransferringOrderUpdateStatusDTO.getId());
            psiTransferringOrderStatus.setTransferringStatus(detailDTO.getTransferringStatus());
            psiTransferringOrderStatus.setProductCount(detailDTO.getProductCount());
            psiTransferringOrderStatus.setGmtModified(new Date());
            psiTransferringOrderStatus.setUpdateBy(psiTransferringOrderUpdateStatusDTO.getUpdateBy());

            resultList.add(psiTransferringOrderStatus);
        }

        this.saveOrUpdateBatch(resultList);

        return resultList;
    }

    @Override
    public List<PsiTransferringOrderUpdateStatusDetailDTO> queryByTransferringId(Set<Long> ids) {
        List<PsiTransferringOrderUpdateStatusDetailDTO> transferringOrderUpdateStatusDetailDTOList = CollUtil.newArrayList();
        if (CollUtil.isNotEmpty(ids)) {
            LambdaQueryWrapper<PsiTransferringOrderStatus> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(PsiTransferringOrderStatus::getTransferringOrderId, ids);
            queryWrapper.eq(PsiTransferringOrderStatus::getIsDeleted, 0);
            queryWrapper.orderByAsc(PsiTransferringOrderStatus::getTransferringStatus);

            List<PsiTransferringOrderStatus> transferringOrderStatusList = this.list(queryWrapper);
            for (PsiTransferringOrderStatus transferringOrderStatus : transferringOrderStatusList) {
                PsiTransferringOrderUpdateStatusDetailDTO detailDTO = new PsiTransferringOrderUpdateStatusDetailDTO();
                detailDTO.setTransferringOrderId(transferringOrderStatus.getTransferringOrderId());
                detailDTO.setTransferringStatus(transferringOrderStatus.getTransferringStatus());
                detailDTO.setProductCount(transferringOrderStatus.getProductCount());

                transferringOrderUpdateStatusDetailDTOList.add(detailDTO);
            }
        }

        return transferringOrderUpdateStatusDetailDTOList;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<PsiTransferringOrderStatus> saveTransferringOrderStatus(PsiTransferringOrder psiTransferringOrder) {
        List<PsiTransferringOrderStatus> psiTransferringOrderStatusList = new ArrayList<>();
        for (TransferChangeEnum transferChangeEnum : TransferChangeEnum.values()) {
            PsiTransferringOrderStatus psiTransferringOrderStatus = new PsiTransferringOrderStatus();
            psiTransferringOrderStatus.setTransferringOrderId(psiTransferringOrder.getId());
            psiTransferringOrderStatus.setTransferringStatus(transferChangeEnum.getCode());
            psiTransferringOrderStatus.setProductCount(0);
            if (transferChangeEnum.getCode().equals(TransferChangeEnum.INKJET_PRINTING.getCode())) {
                psiTransferringOrderStatus.setProductCount(psiTransferringOrder.getProductCount());
            }
            psiTransferringOrderStatus.setCreateBy(psiTransferringOrder.getCreateBy());
            psiTransferringOrderStatus.setGmtCreate(psiTransferringOrder.getGmtCreate());
            psiTransferringOrderStatus.setGmtModified(psiTransferringOrder.getGmtModified());

            psiTransferringOrderStatusList.add(psiTransferringOrderStatus);
        }

        this.saveBatch(psiTransferringOrderStatusList);

        return psiTransferringOrderStatusList;
    }
}
