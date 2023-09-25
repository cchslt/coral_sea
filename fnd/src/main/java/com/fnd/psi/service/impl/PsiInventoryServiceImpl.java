package com.fnd.psi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.dto.inventory.PsiInventoryDTO;
import com.fnd.psi.mapper.PsiInventoryMapper;
import com.fnd.psi.model.PsiInventory;
import com.fnd.psi.service.PsiInventoryService;
import com.fnd.psi.utils.CopyBeanUtils;
import com.fnd.psi.utils.ResultUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 14:50
 * @Desc:
 * @See:
 */
@Slf4j
@Service("psiInventoryService")
@AllArgsConstructor
public class PsiInventoryServiceImpl extends ServiceImpl<PsiInventoryMapper, PsiInventory> implements PsiInventoryService {

    private ResultUtils resultUtils;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public PsiInventory addOrUpdate(PsiInventoryDTO psiInventoryDTO) {
        PsiInventory psiInventory = findPsiInventoryBySkuAndWarehouseId(psiInventoryDTO.getProductSkuId(), psiInventoryDTO.getWarehouseId());
        if (psiInventory == null) {
            psiInventory = CopyBeanUtils.convert(psiInventoryDTO, PsiInventory.class);
            this.save(psiInventory);
        } else {
            psiInventoryDTO.setId(psiInventory.getId());
            baseMapper.addUpdateInventory(psiInventoryDTO);
        }
        return psiInventory;
    }

    @Override
    public PsiInventory subOrUpdate(PsiInventoryDTO psiInventoryDTO) {
        PsiInventory psiInventory = findPsiInventoryBySkuAndWarehouseId(psiInventoryDTO.getProductSkuId(), psiInventoryDTO.getWarehouseId());

        psiInventoryDTO.setId(psiInventory.getId());
        baseMapper.subUpdateInventory(psiInventoryDTO);

        return psiInventory;
    }

    public PsiInventory findPsiInventoryBySkuAndWarehouseId(Long skuId, Long warehouseId) {
        LambdaQueryWrapper<PsiInventory> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(PsiInventory::getProductSkuId, skuId);
        queryWrapper.eq(PsiInventory::getWarehouseId, warehouseId);
        queryWrapper.eq(PsiInventory::getIsDeleted, 0);

        final PsiInventory psiInventory = this.getOne(queryWrapper);
        return psiInventory;
    }


}
