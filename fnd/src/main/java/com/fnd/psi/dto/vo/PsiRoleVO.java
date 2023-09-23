package com.fnd.psi.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * @Author: xu_xin
 * @Date: 2022/1/26/026 18:20
 */
@Data
public class PsiRoleVO implements Serializable {


    @ApiModelProperty("id")
    private Long id;

    /**
     *  角色名字
     *
     */
    @ApiModelProperty("角色名字")
    private String roleName;


    /**
     *  状态：0禁用，1启用
     *
     */
    @ApiModelProperty("状态：0禁用，1启用")
    private Integer status;


    /**
     *  备注
     *
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 权限标识集合
     */
    @ApiModelProperty("权限标识集合")
    private List<PermissionDTO> menuData;

    @ApiModelProperty("用户拥有的权限标识集合")
    private List<Long> permissionIds;

    @ApiModelProperty("按钮权限标识集合")
    private List<String> permissions;


    @ApiModelProperty("虚拟按钮权限标识集合")
    private List<Long> virtualPermissionIds;






}
