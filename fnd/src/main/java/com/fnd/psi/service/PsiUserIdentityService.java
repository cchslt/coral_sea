package com.fnd.psi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.constant.UserIdentityTypeEnum;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.model.PsiUserIdentity;


/**
 * @Date: 2022/2/18/018 16:49
 */
public interface PsiUserIdentityService extends IService<PsiUserIdentity> {


    /**
     * 根据外部ID查询PSI用户
     *
     * @param sourceId
     * @return
     */
    ResultVo<PsiUserIdentity> getPsiUserIdentityBySourceId(Long sourceId, UserIdentityTypeEnum identityType);

    /**
     *
     * @param psiUserId
     * @param identityType
     * @return
     */
    ResultVo<PsiUserIdentity> getPsiUserIdentityByPsiUserId(Long psiUserId, UserIdentityTypeEnum identityType);

    /**
     * 根据用户id查询身份
     * @param psiUserId
     * @return
     */
    PsiUserIdentity getPsiUserIdentityByPsiUserId(Long psiUserId);

    /**
     * 判断PSI用户是否是R
     * @param psiUserId
     * @return
     */
    Boolean checkIsdRShopByPsiUserId(Long psiUserId);


}
