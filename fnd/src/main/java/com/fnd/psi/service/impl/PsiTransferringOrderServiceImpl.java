package com.fnd.psi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.constant.StorageStatusEnum;
import com.fnd.psi.dto.PsiTransferringOrderDTO;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.user.PsiUserDTO;
import com.fnd.psi.mapper.PsiTransferringOrderMapper;
import com.fnd.psi.model.PsiTransferringOrder;
import com.fnd.psi.security.FndSecurityContextUtil;
import com.fnd.psi.service.PsiTransferringOrderService;
import com.fnd.psi.utils.CopyBeanUtils;
import com.fnd.psi.utils.ResultUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    @Override
    public ResultVo<PsiTransferringOrderDTO> save(PsiTransferringOrderDTO psiTransferringOrderDTO) {
        PsiUserDTO user = FndSecurityContextUtil.getContext().getPsiUserInfoDTO().getUser();
        buildPsiTransferringOrderDTO(psiTransferringOrderDTO, user);

        PsiTransferringOrder psiTransferringOrder = CopyBeanUtils.convert(psiTransferringOrderDTO, PsiTransferringOrder.class);
        this.save(psiTransferringOrder);

        return resultUtils.returnSuccess(psiTransferringOrder);
    }

    private void buildPsiTransferringOrderDTO(PsiTransferringOrderDTO psiTransferringOrderDTO, PsiUserDTO user) {
        psiTransferringOrderDTO.setRelationStorageStatus(StorageStatusEnum.NOT_WAREHOUSED.getCode());
        psiTransferringOrderDTO.setReceivedStorageCount(0);
        psiTransferringOrderDTO.setNotYetStorageCount(psiTransferringOrderDTO.getProductCount());
        psiTransferringOrderDTO.setBelongUserId(user.getBelongUserId());
        psiTransferringOrderDTO.setCreateBy(user.getId());
        psiTransferringOrderDTO.setUpdateBy(user.getId());
    }


}
