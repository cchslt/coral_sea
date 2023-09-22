package com.fnd.psi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.dto.ResultVo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: xu_xin
 * @Date: 2022/1/26/026 17:30
 */
public interface PermissionService extends IService<PsiPermission> {

    /**
     * 资源添加
     *
     * @param
     * @return
     */
    ResultVo updatePermission(PermissionVO permissionDto, HttpServletRequest request);



    /**
     * 资源列表
     *
     * @return
     */
    ResultVo<List<PermissionDTO>> permissionList();


    /**
     * 获取角色权限 包含Button按钮的
     * @param roleIds
     * @return
     */
    List<PermissionDTO> getRolePermissionByRolesContainButton(List<Long> roleIds) ;


    /**
     * 权限树
     * @param rolePermissionByRoles
     * @return
     */
    List<PermissionDTO>  builderPermissionTree(List<PermissionDTO> rolePermissionByRoles);

    /**
     *  获取角色权限
     * @return
     */
    HashMap<String, List<PermissionDTO>> getRolePermissionByRoles(List<Long> roleIds,  PsiUserInfoDTO psiUserInfoDTO);

    /**
     * 过滤菜单
     * @param listResultVoData
     * @return
     */
    List<PermissionDTO> getPermissionDTO(List<PermissionDTO> listResultVoData, PsiUserInfoDTO psiUserInfoDTO);
}