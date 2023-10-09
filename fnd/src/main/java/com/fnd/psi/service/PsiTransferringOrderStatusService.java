package com.fnd.psi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.dto.PsiTransferringOrderUpdateStatusDTO;
import com.fnd.psi.dto.PsiTransferringOrderUpdateStatusDetailDTO;
import com.fnd.psi.model.PsiTransferringOrder;
import com.fnd.psi.model.PsiTransferringOrderStatus;

import java.util.List;
import java.util.Set;

/**
 * @Author: chenchaohai
 * @Date: 2023-10-09 14:01
 * @Desc:
 * @See:
 */
public interface PsiTransferringOrderStatusService extends IService<PsiTransferringOrderStatus> {

    /**
     * 修改状态
     * @param psiTransferringOrderUpdateStatusDTO
     * @return
     */
    List<PsiTransferringOrderStatus> handleTransferringOrderStatus(PsiTransferringOrderUpdateStatusDTO psiTransferringOrderUpdateStatusDTO);


    List<PsiTransferringOrderUpdateStatusDetailDTO> queryByTransferringId(Set<Long> ids);

    /**
     * 保存
     * @param psiTransferringOrder
     * @return
     */
    List<PsiTransferringOrderStatus> saveTransferringOrderStatus(PsiTransferringOrder psiTransferringOrder);
}
