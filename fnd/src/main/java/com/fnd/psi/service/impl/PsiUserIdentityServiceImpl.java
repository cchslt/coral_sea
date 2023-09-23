package com.fnd.psi.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.constant.CommonConstant;
import com.fnd.psi.constant.UserIdentityTypeEnum;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.mapper.PsiUserIdentityMapper;
import com.fnd.psi.model.PsiUserIdentity;
import com.fnd.psi.service.PsiUserIdentityService;
import com.fnd.psi.utils.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date: 2022/2/18/018 16:50
 */
@Slf4j
@Service("psiUserIdentityService")
public class PsiUserIdentityServiceImpl extends ServiceImpl<PsiUserIdentityMapper, PsiUserIdentity> implements PsiUserIdentityService {


    @Override
    public ResultVo<PsiUserIdentity> getPsiUserIdentityBySourceId(Long sourceId, UserIdentityTypeEnum identityType) {

        final List<PsiUserIdentity> psiUserIdentitys = list(new LambdaQueryWrapper<PsiUserIdentity>()
                .eq(PsiUserIdentity::getIdentityType, identityType.getCode())
                .eq(PsiUserIdentity::getIsDeleted, false)
                .eq(PsiUserIdentity::getSourceId, sourceId));
        /**
         * todo 之后加判断
         */
        return ResultVoUtil.success(psiUserIdentitys.get(0));
    }

    @Override
    public ResultVo<PsiUserIdentity> getPsiUserIdentityByPsiUserId(Long psiUserId, UserIdentityTypeEnum identityType) {
        PsiUserIdentity psiUserIdentity = new PsiUserIdentity();
        final List<PsiUserIdentity> psiUserIdentitys = list(new LambdaQueryWrapper<PsiUserIdentity>()
                .eq(PsiUserIdentity::getIdentityType, identityType.getCode())
                .eq(PsiUserIdentity::getIsDeleted, false)
                .eq(PsiUserIdentity::getUserId, psiUserId));
        if (CollUtil.isNotEmpty(psiUserIdentitys)) {
            psiUserIdentity = psiUserIdentitys.get(0);
        }
        return ResultVoUtil.success(psiUserIdentity);
    }

    @Override
    public PsiUserIdentity getPsiUserIdentityByPsiUserId(Long psiUserId) {
        PsiUserIdentity psiUserIdentity = new PsiUserIdentity();
        final List<PsiUserIdentity> psiUserIdentitys = list(new LambdaQueryWrapper<PsiUserIdentity>()
                .eq(PsiUserIdentity::getIsDeleted, false)
                .eq(PsiUserIdentity::getUserId, psiUserId));
        if (CollUtil.isNotEmpty(psiUserIdentitys)) {
            psiUserIdentity = psiUserIdentitys.get(0);
        }
        return psiUserIdentity;
    }

    @Override
    public Boolean checkIsdRShopByPsiUserId(Long psiUserId) {

       Integer  haveCount = count(new LambdaQueryWrapper<PsiUserIdentity>()
                .eq(PsiUserIdentity::getIdentityType, UserIdentityTypeEnum.R_SHOP.getCode())
                .eq(PsiUserIdentity::getIsDeleted, CommonConstant.IS_DELETED_FALSE)
                .eq(PsiUserIdentity::getUserId, psiUserId));
        if(ObjectUtil.isNotNull(haveCount) && haveCount>0 ){
            return true;
        }
        return false;
    }

}
