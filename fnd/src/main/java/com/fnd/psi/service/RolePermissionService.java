package com.fnd.psi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.vo.UpdateRolePermissionDTO;
import com.fnd.psi.model.PsiRolePermission;


public interface RolePermissionService extends IService<PsiRolePermission> {


    /**
     * 根据角色id查询资源(只展示子菜单)
     *
     * @param roleId
     * @return
     */
    ResultVo findPermissionByRoleId(Long roleId);


    ResultVo updateRolePermission(UpdateRolePermissionDTO updateRolePermissionDTO);
}