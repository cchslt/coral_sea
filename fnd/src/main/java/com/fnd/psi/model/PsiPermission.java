package com.fnd.psi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@TableName(value="t_psi_permission")
@Data
public class PsiPermission implements Serializable {
    /**
     * 主键 默认主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)

    /**
     *  id
     *
     */
    private Long id;

    /**
     * 按钮名称
     */
    private String menuName;


    /**
     *  api权限名
     *
     */
    private String name;


    /**
     *  api权限资源
     *
     */
    private String path;


    /**
     *  icon
     *
     */
    private String icon;


    /**
     *  权限父节点（0为根节点）
     *
     */
    private Long parentId;


    /**
     *  菜单类型 （0菜单 1按钮）
     *
     */
    private Boolean type;

    /**
     * 是否是虚拟节点
     */
    private Boolean virtual;

    /**
     *  状态：0禁用，1启用
     *
     */
    private Integer status;


    /**
     *  优先级，越大，同级显示的时候越靠前
     *
     */
    private Integer priority;


    /**
     *  redirect
     *
     */
    private String redirect;

    private String inId;
    /**
     *  备注
     *
     */
    private String remark;


    private Boolean hideInMenu;

    /**
     * 是否展示菜单（以后设置按钮权限后可废除）
     */
    private  Boolean show;

    /**
     *  英文
     *
     */
    private String en;


    /**
     *  创建时间
     *
     */
    private Date gmtModified;


    /**
     *  修改时间
     *
     */
    private Date gmtCreate;


    /**
     *  是否删除
     *
     */
    private Boolean isDeleted;


}
