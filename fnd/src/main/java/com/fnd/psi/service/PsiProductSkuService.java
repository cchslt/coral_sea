package com.fnd.psi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.dto.PageDTO;
import com.fnd.psi.dto.ResultVo;
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
     *
     * @param id
     * @return
     */
    ResultVo delete(Long id);

    /**
     * 详情
     * @param id
     * @return
     */
    ResultVo<PsiProductSkuDTO> detail(Long id);

    /**
     * 查询列表
     * @param productSkuVO
     * @return
     */
    List<PsiProductSkuDTO> list(PsiProductSkuVO productSkuVO);

    /**
     * 分页查询
     * @param productSkuVO
     * @return
     */
    PageDTO<PsiProductSkuDTO> listPage(PsiProductSkuVO productSkuVO);
}