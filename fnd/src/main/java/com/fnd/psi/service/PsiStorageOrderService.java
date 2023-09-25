package com.fnd.psi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.dto.storage.PsiStorageOrderDTO;
import com.fnd.psi.model.PsiStorageOrder;

public interface PsiStorageOrderService extends IService<PsiStorageOrder> {

    /**
     * 生成入库单
     * @param psiStorageOrderDTO
     */
    PsiStorageOrder createStorageOrder(PsiStorageOrderDTO psiStorageOrderDTO);
}
