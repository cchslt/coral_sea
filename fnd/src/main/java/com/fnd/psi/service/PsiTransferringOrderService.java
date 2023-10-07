package com.fnd.psi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.dto.*;
import com.fnd.psi.model.PsiTransferringOrder;

public interface PsiTransferringOrderService extends IService<PsiTransferringOrder> {

    /**
     * 调拨单信息 列表
     * @param psiTransferringOrderQuery
     * @return
     */
    ResultVo<PageDTO<PsiTransferringOrderDTO>> listPage(PsiTransferringOrderQuery psiTransferringOrderQuery);

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
     * 仓库调拨
     * @param psiTransferringOrderUpdateDTO
     * @return
     */
    ResultVo<PsiTransferringOrderDTO> transferringForWarehouse(PsiTransferringOrderUpdateDTO psiTransferringOrderUpdateDTO);

    /**
     * 修改状态
     * @param psiTransferringOrderUpdateStatusDTO
     * @return
     */
    ResultVo<PsiTransferringOrderDTO> updateStatus(PsiTransferringOrderUpdateStatusDTO psiTransferringOrderUpdateStatusDTO);
}
