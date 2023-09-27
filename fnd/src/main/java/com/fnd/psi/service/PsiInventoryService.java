package com.fnd.psi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.dto.inventory.PsiInventoryDTO;
import com.fnd.psi.dto.vo.PsiInventoryVo;
import com.fnd.psi.dto.vo.PsiProductInventoryVO;
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
     * @param changeCode
     */
    PsiInventory addOrUpdate(PsiInventoryDTO psiInventoryDTO, String changeCode);

    /**
     * 出库
     * @param psiInventoryDTO
     * @param changeCode
     * @return
     */
    PsiInventory subOrUpdate(PsiInventoryDTO psiInventoryDTO, String changeCode);

    /**
     * 查询仓库的库存
     * @param permissionVO
     * @return
     */
    PsiProductInventoryVO permissionVO(PsiInventoryVo permissionVO);
}
