package com.fnd.psi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@TableName(value="t_psi_role_permission")
@Data
public class PsiRolePermission implements Serializable {

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
     *  角色id
     *
     */
    private Long roleId;


    /**
     *  权限id
     *
     */
    private Long permissionId;


    /**
     *  修改时间
     *
     */
    private Date gmtModified;


    /**
     *  创建时间
     *
     */
    private Date gmtCreate;


    /**
     *  是否被删除
     *
     */
    private Boolean isDeleted;


}
