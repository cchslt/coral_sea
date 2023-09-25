package com.fnd.psi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fnd.psi.dto.inventory.PsiInventoryDTO;
import com.fnd.psi.model.PsiInventory;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 14:50
 * @Desc:
 * @See:
 */
public interface PsiInventoryMapper extends BaseMapper<PsiInventory> {

    /**
     *
     * @param psiInventoryDTO
     */
    void addUpdateInventory(@Param("psiInventoryDTO") PsiInventoryDTO psiInventoryDTO);

    /**
     *
     * @param psiInventoryDTO
     */
    void subUpdateInventory(@Param("psiInventoryDTO") PsiInventoryDTO psiInventoryDTO);
}