package com.fnd.psi.controller;

import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.vo.PermissionVO;
import com.fnd.psi.security.FndPreAuthorize;
import com.fnd.psi.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/permission")
@Api(value = "role", tags = "获取权限列表")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    @FndPreAuthorize
    @ApiOperation("菜单添加/修改")
    @RequestMapping(value = "/updatePermission", method = RequestMethod.POST)
    public ResultVo updatePermission(@RequestBody PermissionVO permissionVO, HttpServletRequest request) {
        return permissionService.updatePermission(permissionVO,request);
    }



}

