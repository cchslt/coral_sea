package com.fnd.psi.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.constant.ErrorCodeConstants;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.product.PsiProductSkuDTO;
import com.fnd.psi.dto.vo.PsiProductSkuVO;
import com.fnd.psi.exception.XXException;
import com.fnd.psi.mapper.PsiProductSkuMapper;
import com.fnd.psi.model.PsiProductSku;
import com.fnd.psi.security.FndSecurityContextUtil;
import com.fnd.psi.service.PsiProductSkuService;
import com.fnd.psi.utils.CopyBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 10:41
 * @Desc:
 * @See:
 */
@Slf4j
@Service("psiProductSkuService")
public class PsiProductSkuServiceImpl extends ServiceImpl<PsiProductSkuMapper, PsiProductSku> implements PsiProductSkuService {


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public PsiProductSkuDTO save(PsiProductSkuDTO psiProductSku) {
        if (psiProductSku.getId() != null) {
            return update(psiProductSku);
        }
        PsiProductSku exitSku = findBySkuCode(psiProductSku.getSkuCode());
        if (ObjectUtil.isNotNull(exitSku)) {
            throw new XXException(ErrorCodeConstants.PRODUCT_CODE_IS_EXIT);
        }

        PsiProductSku productSku = CopyBeanUtils.convert(psiProductSku, PsiProductSku.class);
        final Long userId = FndSecurityContextUtil.getContext().getId();
        productSku.setCreateUserId(userId);
        productSku.setModifyUserId(userId);
        this.save(productSku);

        return CopyBeanUtils.convert(productSku, PsiProductSkuDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public PsiProductSkuDTO update(PsiProductSkuDTO psiProductSku) {
        PsiProductSku productSku = this.getById(psiProductSku.getId());
        if (ObjectUtil.isNull(productSku)) {
            throw new XXException(ErrorCodeConstants.PRODUCT_CODE_IS_NOT_EXIT);
        }
        if (!psiProductSku.getSkuCode().equals(productSku.getSkuCode())) {
            throw new XXException(ErrorCodeConstants.PRODUCT_CODE_IS_NOT_EQUAL);
        }

        productSku.setSkuProductName(psiProductSku.getSkuProductName());
        final Long userId = FndSecurityContextUtil.getContext().getId();
        productSku.setModifyUserId(userId);

        return psiProductSku;
    }


    public PsiProductSku findBySkuCode(String skuCode) {
        LambdaQueryWrapper<PsiProductSku> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(PsiProductSku::getSkuCode, skuCode);
        lambdaQueryWrapper.eq(PsiProductSku::getIsDeleted, 0);

        return this.getOne(lambdaQueryWrapper);
    }


    @Override
    public List<PsiProductSkuDTO> list(PsiProductSkuVO productSkuVO) {
        LambdaQueryWrapper<PsiProductSku> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(StrUtil.isNotBlank(productSkuVO.getSkuCode()), PsiProductSku::getSkuCode, productSkuVO.getSkuCode());
        lambdaQueryWrapper.like(StrUtil.isNotBlank(productSkuVO.getSkuProductName()), PsiProductSku::getSkuProductName, productSkuVO.getSkuProductName());
        lambdaQueryWrapper.eq(PsiProductSku::getIsDeleted, 0);
        lambdaQueryWrapper.orderByDesc(PsiProductSku::getGmtCreate);

        return CopyBeanUtils.copyList(baseMapper.selectList(lambdaQueryWrapper), PsiProductSkuDTO.class);
    }

}
