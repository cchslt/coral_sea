package com.fnd.psi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.dto.inventory.PsiInventoryDTO;
import com.fnd.psi.dto.storage.PsiStorageOrderDTO;
import com.fnd.psi.mapper.PsiStorageOrderMapper;
import com.fnd.psi.model.PsiInventory;
import com.fnd.psi.model.PsiStorageOrder;
import com.fnd.psi.service.PsiInventoryService;
import com.fnd.psi.service.PsiStorageOrderService;
import com.fnd.psi.utils.CopyBeanUtils;
import com.fnd.psi.utils.PSICodeUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 17:30
 * @Desc:
 * @See:
 */
@Slf4j
@Service("psiStorageOrderService")
@AllArgsConstructor
public class PsiStorageOrderServiceImpl extends ServiceImpl<PsiStorageOrderMapper, PsiStorageOrder> implements PsiStorageOrderService {

    private PsiInventoryService psiInventoryService;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public PsiStorageOrder createStorageOrder(PsiStorageOrderDTO psiStorageOrderDTO) {
        PsiStorageOrder psiStorageOrder = CopyBeanUtils.convert(psiStorageOrderDTO, PsiStorageOrder.class);
        psiStorageOrder.setStorageOrderCode(PSICodeUtils.getStorageOrderCode());
        this.save(psiStorageOrder);

        // 操作库存
        PsiInventoryDTO psiInventoryDTO = buildPsiInventoryDTO(psiStorageOrder);
        psiInventoryService.addOrUpdate(psiInventoryDTO, psiStorageOrder.getStorageOrderCode());

        return psiStorageOrder;
    }

    private PsiInventoryDTO buildPsiInventoryDTO(PsiStorageOrder psiStorageOrder) {
        PsiInventoryDTO psiInventoryDTO = new PsiInventoryDTO();
        psiInventoryDTO.setWarehouseId(psiStorageOrder.getWarehouseId());
        psiInventoryDTO.setProductSkuId(psiStorageOrder.getProductSkuId());
        psiInventoryDTO.setProductSkuCode(psiStorageOrder.getProductSkuCode());
        psiInventoryDTO.setAvailableQuantity(psiStorageOrder.getProductCount());
        psiInventoryDTO.setSellableQuantity(psiStorageOrder.getProductCount());
        psiInventoryDTO.setCreateBy(psiStorageOrder.getCreateBy());
        psiInventoryDTO.setUpdateBy(psiStorageOrder.getUpdateBy());

        return psiInventoryDTO;

    }
}
