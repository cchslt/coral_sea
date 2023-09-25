package com.fnd.psi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.dto.inventory.PsiInventoryDTO;
import com.fnd.psi.model.PsiInventory;

/**
 * @Date:  2023-09-25
 * @Desc:  库存主表 Service
 * @See:
 */
public interface PsiInventoryService extends IService<PsiInventory> {


    /**
     * 入库
     * @param psiInventoryDTO
     */
    PsiInventory addOrUpdate(PsiInventoryDTO psiInventoryDTO);

    /**
     * 出库
     * @param psiInventoryDTO
     * @return
     */
    PsiInventory subOrUpdate(PsiInventoryDTO psiInventoryDTO);
}
