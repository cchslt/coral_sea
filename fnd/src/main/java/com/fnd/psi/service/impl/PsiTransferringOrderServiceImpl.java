package com.fnd.psi.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.constant.StorageStatusEnum;
import com.fnd.psi.dto.PsiTransferringOrderDTO;
import com.fnd.psi.dto.PsiTransferringOrderUpdateDTO;
import com.fnd.psi.dto.PsiTransferringOrderUpdateStatusDTO;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.storage.PsiStorageOrderDTO;
import com.fnd.psi.dto.user.PsiUserDTO;
import com.fnd.psi.mapper.PsiTransferringOrderMapper;
import com.fnd.psi.model.PsiTransferringOrder;
import com.fnd.psi.security.FndSecurityContextUtil;
import com.fnd.psi.service.PsiStorageOrderService;
import com.fnd.psi.service.PsiTransferringOrderService;
import com.fnd.psi.utils.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultVo<PsiTransferringOrderDTO> save(PsiTransferringOrderDTO psiTransferringOrderDTO) {
        PsiUserDTO user = FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser();
        buildPsiTransferringOrderDTO(psiTransferringOrderDTO, user);

        PsiTransferringOrder psiTransferringOrder = CopyBeanUtils.convert(psiTransferringOrderDTO, PsiTransferringOrder.class);
        psiTransferringOrder.setTransferCode(PSICodeUtils.getTransferringOrderCode());
        this.save(psiTransferringOrder);

        return resultUtils.returnSuccess(psiTransferringOrder);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultVo<PsiTransferringOrderDTO> transferring(PsiTransferringOrderUpdateDTO psiTransferringOrderUpdateDTO) {
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
        PsiStorageOrderDTO psiStorageOrderDTO = buildStorageOrderDTO(psiTransferringOrder, psiTransferringOrderUpdateDTO);
        log.info("生成入库单， psiStorageOrderDTO： {}", JSONUtil.toJsonStr(psiStorageOrderDTO));
        psiStorageOrderService.createStorageOrder(psiStorageOrderDTO);

        return  resultUtils.returnSuccess(CopyBeanUtils.convert(psiTransferringOrder, PsiTransferringOrderDTO.class));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultVo<PsiTransferringOrderDTO> updateStatus(PsiTransferringOrderUpdateStatusDTO psiTransferringOrderUpdateStatusDTO) {
        final PsiTransferringOrder psiTransferringOrder = this.getById(psiTransferringOrderUpdateStatusDTO.getId());
        if (psiTransferringOrder == null) {
            return ResultVoUtil.error("不存在调拨单");
        }

        psiTransferringOrder.setTransferringStatus(psiTransferringOrderUpdateStatusDTO.getTransferringStatus());
        psiTransferringOrder.setRemarks(psiTransferringOrderUpdateStatusDTO.getRemarks());
        psiTransferringOrder.setUpdateBy(FndSecurityContextUtil.getContext().getId());

        this.updateById(psiTransferringOrder);

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


    private PsiStorageOrderDTO buildStorageOrderDTO(PsiTransferringOrder psiTransferringOrder, PsiTransferringOrderUpdateDTO psiTransferringOrderUpdateDTO) {
        PsiStorageOrderDTO psiStorageOrderDTO = new PsiStorageOrderDTO();
        psiStorageOrderDTO.setWarehouseId(psiTransferringOrderUpdateDTO.getTargetWarehouseId());
        psiStorageOrderDTO.setSourceId(psiTransferringOrder.getId());
        psiStorageOrderDTO.setSourceCode(psiTransferringOrder.getTransferCode());
        psiStorageOrderDTO.setSourceBusinessTime(psiTransferringOrder.getGmtModified());
        psiStorageOrderDTO.setProductCount(psiTransferringOrderUpdateDTO.getProductCount());
        psiStorageOrderDTO.setReceivedCount(psiTransferringOrderUpdateDTO.getProductCount());
        psiStorageOrderDTO.setStorageStatus(StorageStatusEnum.ALL_WAREHOUSING.getCode());
        psiStorageOrderDTO.setBelongUserId(psiTransferringOrder.getBelongUserId());
        psiStorageOrderDTO.setCreateBy(psiTransferringOrder.getUpdateBy());
        psiStorageOrderDTO.setUpdateBy(psiTransferringOrder.getUpdateBy());

        return psiStorageOrderDTO;
    }
}
