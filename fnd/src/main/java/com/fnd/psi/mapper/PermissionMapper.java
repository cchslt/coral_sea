package com.fnd.psi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fnd.psi.dto.vo.PermissionDTO;
import com.fnd.psi.dto.vo.PermissionVO;
import com.fnd.psi.model.PsiPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: xu_xin
 * @Date: 2022/1/26/026 17:30
 */
public interface PermissionMapper extends BaseMapper<PsiPermission> {

    List<PermissionVO> listMenusByRoleId(@Param("roleId") Long roleId);


    List<PermissionDTO> getRolePermissionByRoles(@Param("roleIds") List<Long> roleIds);


    List<PermissionDTO> getRolePermission();
}