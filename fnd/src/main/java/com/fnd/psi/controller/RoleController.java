package com.fnd.psi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.vo.PsiRoleVO;
import com.fnd.psi.dto.vo.RoleListDTO;
import com.fnd.psi.dto.vo.UpdateRolePermissionDTO;
import com.fnd.psi.model.PsiRole;
import com.fnd.psi.security.FndPreAuthorize;
import com.fnd.psi.service.RolePermissionService;
import com.fnd.psi.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/role")
@Api(value = "role", tags = "角色模块")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @FndPreAuthorize
    @ApiOperation("角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultVo<IPage<PsiRole>> roleList(@RequestBody RoleListDTO roleListDTO) {
        return roleService.findUserAllRole(roleListDTO);
    }

    @FndPreAuthorize
    @ApiOperation("角色添加/修改")
    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    public ResultVo roleAdd(@RequestBody PsiRoleVO psiRoleVO) {
        return roleService.saveRole(psiRoleVO);
    }

    @FndPreAuthorize
    @ApiOperation("查看角色详情")
    @GetMapping(value = "/findRoleById")
    public ResultVo<PsiRoleVO> findRoleById(Long roleId) {
        return roleService.findRoleById(roleId);
    }

    @FndPreAuthorize
    @ApiOperation("设置修改角色权限")
    @RequestMapping(value = "/updateRolePermission", method = RequestMethod.POST)
    public ResultVo updateRolePermission(@RequestBody UpdateRolePermissionDTO updateRolePermissionDTO) {
        return rolePermissionService.updateRolePermission(updateRolePermissionDTO);
    }

}
