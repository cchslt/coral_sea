package com.fnd.psi.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
public class UpdateRolePermissionDTO {

    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("权限id集合")
    private List<Long> permissionIds;

}
