package com.fnd.psi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.constant.InventoryChangeEnum;
import com.fnd.psi.constant.InventoryChangeTypeEnum;
import com.fnd.psi.dto.inventory.PsiInventoryDTO;
import com.fnd.psi.dto.vo.PsiInventoryVo;
import com.fnd.psi.dto.vo.PsiProductInventoryVO;
import com.fnd.psi.exception.XXException;
import com.fnd.psi.mapper.PsiInventoryMapper;
import com.fnd.psi.model.InventoryChangeLog;
import com.fnd.psi.model.PsiInventory;
import com.fnd.psi.model.PsiProductSku;
import com.fnd.psi.service.InventoryChangeLogService;
import com.fnd.psi.service.PsiInventoryService;
import com.fnd.psi.service.PsiProductSkuService;
import com.fnd.psi.utils.CopyBeanUtils;
import com.fnd.psi.utils.IntegerUtils;
import com.fnd.psi.utils.ResultUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    private InventoryChangeLogService inventoryChangeLogService;
    private PsiProductSkuService psiProductSkuService;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public PsiInventory addOrUpdate(PsiInventoryDTO psiInventoryDTO, String changeCode) {
        PsiInventory psiInventory = findPsiInventoryBySkuAndWarehouseId(psiInventoryDTO.getProductSkuId(), psiInventoryDTO.getWarehouseId());
        if (psiInventory == null) {
            psiInventory = CopyBeanUtils.convert(psiInventoryDTO, PsiInventory.class);
            this.save(psiInventory);
        } else {
            psiInventoryDTO.setId(psiInventory.getId());
            baseMapper.addUpdateInventory(psiInventoryDTO);
        }

        //库存变化日志
        InventoryChangeLog inventoryChangeLog = buildInventoryChangeLog(psiInventory, psiInventoryDTO.getSellableQuantity(), changeCode, InventoryChangeEnum.ADD, InventoryChangeTypeEnum.SELLABLE_STOCK);
        inventoryChangeLogService.save(inventoryChangeLog);

        return psiInventory;
    }

    @Override
    public PsiInventory subOrUpdate(PsiInventoryDTO psiInventoryDTO, String changeCode) {
        PsiInventory psiInventory = findPsiInventoryBySkuAndWarehouseId(psiInventoryDTO.getProductSkuId(), psiInventoryDTO.getWarehouseId());
        if (psiInventory == null) {
            throw new XXException("调拨仓库中的商品没有库存！！");
        }

        psiInventoryDTO.setId(psiInventory.getId());
        baseMapper.subUpdateInventory(psiInventoryDTO);

        //库存变化日志
        InventoryChangeLog inventoryChangeLog = buildInventoryChangeLog(psiInventory, psiInventoryDTO.getSellableQuantity(), changeCode, InventoryChangeEnum.REDUCE, InventoryChangeTypeEnum.SELLABLE_STOCK);
        inventoryChangeLogService.save(inventoryChangeLog);

        return psiInventory;
    }

    @Override
    public PsiProductInventoryVO permissionVO(PsiInventoryVo permissionVO) {
        PsiProductInventoryVO psiProductInventoryVO = new PsiProductInventoryVO();

        PsiProductSku psiProductSku = Optional.ofNullable(psiProductSkuService.getById(permissionVO.getProductSkuId())).orElse(new PsiProductSku());
        psiProductInventoryVO.setProductSkuId(psiProductSku.getId());
        psiProductInventoryVO.setProductSkuCode(psiProductSku.getSkuCode());
        psiProductInventoryVO.setProductSkuName(psiProductSku.getSkuProductName());

        PsiInventory psiInventory = Optional.ofNullable(findPsiInventoryBySkuAndWarehouseId(psiProductSku.getId(), permissionVO.getWarehouseId())).orElse(new PsiInventory());
        psiProductInventoryVO.setSellableQuantity(IntegerUtils.optionalToZero(psiInventory.getSellableQuantity()));

        return psiProductInventoryVO;
    }

    public PsiInventory findPsiInventoryBySkuAndWarehouseId(Long skuId, Long warehouseId) {
        LambdaQueryWrapper<PsiInventory> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(PsiInventory::getProductSkuId, skuId);
        queryWrapper.eq(PsiInventory::getWarehouseId, warehouseId);
        queryWrapper.eq(PsiInventory::getIsDeleted, 0);

        final PsiInventory psiInventory = this.getOne(queryWrapper);
        return psiInventory;
    }

    /**
     * 构建通用库存变更实体
     *
     * @param psiInventory
     * @param changeSourceCode
     * @param inventoryChangeEnum
     * @param inventoryChangeTypeEnum
     * @param productCount
     * @return
     */
    private InventoryChangeLog buildInventoryChangeLog(PsiInventory psiInventory,
                                                       Integer productCount,
                                                       String changeSourceCode,
                                                       InventoryChangeEnum inventoryChangeEnum,
                                                       InventoryChangeTypeEnum inventoryChangeTypeEnum) {
        InventoryChangeLog inventoryChangeLog = new InventoryChangeLog();
        inventoryChangeLog.setProductSkuId(psiInventory.getProductSkuId());
        inventoryChangeLog.setChangeType(inventoryChangeEnum.getCode());
        inventoryChangeLog.setInventoryType(inventoryChangeTypeEnum.getCode());
        inventoryChangeLog.setWarehouseId(psiInventory.getWarehouseId());
        inventoryChangeLog.setChangeQuantity(productCount);
        inventoryChangeLog.setChangeSourceCode(changeSourceCode);
        inventoryChangeLog.setCreateBy(psiInventory.getUpdateBy());
        inventoryChangeLog.setUpdateBy(psiInventory.getUpdateBy());
        return inventoryChangeLog;
    }
}
