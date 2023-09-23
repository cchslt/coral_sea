package com.fnd.psi.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class PermissionVO {


    /**
     *  id
     *
     */
    @ApiModelProperty("id")
    private Long id;


    /**
     * 按钮名称
     */
    @ApiModelProperty("按钮名称")
    private String menuName;

    /**
     *  api权限名
     *
     */
    @ApiModelProperty("api权限名")
    private String name;


    /**
     *  api权限资源
     *
     */
    @ApiModelProperty("api权限资源")
    private String path;


    /**
     *  icon
     *
     */
    @ApiModelProperty("icon")
    private String icon;


    /**
     *  权限父节点（0为根节点）
     *
     */
    @ApiModelProperty("权限父节点（0为根节点）")
    private Long parentId;


    /**
     *  菜单类型 （0菜单 1按钮）
     *
     */
    @ApiModelProperty("菜单类型 （0菜单 1按钮）")
    private Boolean type;


    /**
     *  状态：0禁用，1启用
     *
     */
    @ApiModelProperty("状态：0禁用，1启用")
    private Integer status;


    /**
     *  优先级，越大，同级显示的时候越靠前
     *
     */
    @ApiModelProperty("优先级，越大，同级显示的时候越靠前")
    private Integer priority;


    /**
     *  redirect
     *
     */
    @ApiModelProperty("redirect")
    private String redirect;


    /**
     *  备注
     *
     */
    @ApiModelProperty("备注")
    private String remark;


    /**
     *  英文
     *
     */
    @ApiModelProperty("英文")
    private String en;


}
