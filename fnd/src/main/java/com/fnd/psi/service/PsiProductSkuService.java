package com.fnd.psi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.dto.product.PsiProductSkuDTO;
import com.fnd.psi.dto.vo.PsiProductSkuVO;
import com.fnd.psi.model.PsiProductSku;

import java.util.List;

public interface PsiProductSkuService extends IService<PsiProductSku> {

    /**
     * 保存商品
     * @param psiProductSku
     * @return
     */
    PsiProductSkuDTO save(PsiProductSkuDTO psiProductSku);

    /**
     * 更新商品
     * @param psiProductSku
     * @return
     */
    PsiProductSkuDTO update(PsiProductSkuDTO psiProductSku);

    /**
     * 查询列表
     * @param productSkuVO
     * @return
     */
    List<PsiProductSkuDTO> list(PsiProductSkuVO productSkuVO);
}