package com.fnd.psi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.dto.PageDTO;
import com.fnd.psi.dto.storage.PsiStorageOrderDTO;
import com.fnd.psi.dto.vo.PsiProductSkuTransferFlowRequestVO;
import com.fnd.psi.dto.vo.PsiProductSkuTransferFlowVO;
import com.fnd.psi.model.PsiStorageOrder;

public interface PsiStorageOrderService extends IService<PsiStorageOrder> {

    /**
     * 生成入库单
     * @param psiStorageOrderDTO
     */
    PsiStorageOrder createStorageOrder(PsiStorageOrderDTO psiStorageOrderDTO);

    /**
     *
     * @param psiStorageOrderDTO
     * @return
     */
    PsiStorageOrder createStorageOrderForwarehouse(PsiStorageOrderDTO psiStorageOrderDTO);

    /**
     * 调拨流水
     * @param psiProductSkuTransferFlowVO
     * @return
     */
    PageDTO<PsiProductSkuTransferFlowVO> transferringFlow(PsiProductSkuTransferFlowRequestVO psiProductSkuTransferFlowVO);
}
