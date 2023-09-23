package com.fnd.psi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.model.PsiUserRole;

import java.util.List;


public interface UserRoleService extends IService<PsiUserRole> {


    /**
     * 获取 角色
     * @param userId
     * @return
     */
    List<PsiUserRole> getUserRole(Long userId);

    /**
     * 关联 角色
     * @param userId
     * @param roles
     */
    void userRoleRelation(Long userId, List<Long> roles);

}