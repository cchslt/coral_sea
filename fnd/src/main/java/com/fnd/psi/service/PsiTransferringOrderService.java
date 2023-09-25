package com.fnd.psi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.dto.PsiTransferringOrderDTO;
import com.fnd.psi.dto.PsiTransferringOrderUpdateDTO;
import com.fnd.psi.dto.PsiTransferringOrderUpdateStatusDTO;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.model.PsiTransferringOrder;

public interface PsiTransferringOrderService extends IService<PsiTransferringOrder> {

    /**
     * 新增调拨单信息
     * @param psiTransferringOrder
     * @return
     */
    ResultVo<PsiTransferringOrderDTO> save(PsiTransferringOrderDTO psiTransferringOrder);

    /**
     * 更新
     * @param psiTransferringOrderUpdateDTO
     * @return
     */
    ResultVo<PsiTransferringOrderDTO> transferring(PsiTransferringOrderUpdateDTO psiTransferringOrderUpdateDTO);

    /**
     * 修改状态
     * @param psiTransferringOrderUpdateStatusDTO
     * @return
     */
    ResultVo<PsiTransferringOrderDTO> updateStatus(PsiTransferringOrderUpdateStatusDTO psiTransferringOrderUpdateStatusDTO);
}
