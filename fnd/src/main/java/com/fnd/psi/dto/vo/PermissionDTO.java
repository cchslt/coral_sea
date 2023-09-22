package com.fnd.psi.dto.vo;

import com.fnd.psi.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: xu_xin
 * @Date: 2022/2/14/014 10:53
 */

@Data
public class PermissionDTO extends BaseDTO {


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
    private Integer type;

    @ApiModelProperty("0未选中，1选中")
    private Integer check = 0;

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
    @ApiModelProperty("优先级，越大，redirect")
    private String redirect;


    /**
     *  备注
     *
     */
    @ApiModelProperty("备注")
    private String remark;

    private Boolean hideInMenu;

    /**
     * 是否展示菜单（以后设置按钮权限后可废除）
     */
    private  Boolean show;

    /**
     * 是否是虚拟节点
     */
    private Boolean virtual;

    /**
     *  英文
     *
     */
    @ApiModelProperty("英文")
    private String en;

    private String inId;

    @ApiModelProperty("子集合")
    private List<PermissionDTO> routes;

}
