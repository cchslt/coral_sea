package com.fnd.psi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.mapper.UserRoleMapper;
import com.fnd.psi.model.PsiUserRole;
import com.fnd.psi.service.UserRoleService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Slf4j
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, PsiUserRole> implements UserRoleService {

    @Override
    public List<PsiUserRole> getUserRole(Long userId) {
        final List<PsiUserRole> psiUserRoles = list(new LambdaQueryWrapper<PsiUserRole>().eq(PsiUserRole::getIsDeleted, false)
                .eq(PsiUserRole::getUserId,userId));
        return psiUserRoles;
    }

    @Override
    @Transactional
    public void userRoleRelation(Long userId, List<Long> roles) {
        final Date date = new Date();
        /**
         * 清了之前的角色
         */
        lambdaUpdate().set(PsiUserRole::getIsDeleted, 1)
                .eq(PsiUserRole::getUserId,userId)
                .update();
        final List<PsiUserRole> psiUserRoleList = Lists.newArrayList();
        roles.forEach(x ->{
            PsiUserRole psiUserRole = new PsiUserRole();
            psiUserRole.setRoleId(x);
            psiUserRole.setUserId(userId);
            psiUserRole.setGmtCreate(date);
            psiUserRole.setGmtModified(date);
            psiUserRole.setIsDeleted(false);
            psiUserRoleList.add(psiUserRole);
        });
        saveBatch(psiUserRoleList);
    }
}
