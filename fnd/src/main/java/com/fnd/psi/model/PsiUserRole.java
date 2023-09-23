package com.fnd.psi.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xu_xin
 * @Date: 2022/1/26/026 17:37
 */
@TableName(value="t_psi_user_role")
@Data
public class PsiUserRole implements Serializable {

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
     *  用户id
     *
     */
    private Long userId;


    /**
     *  角色id
     *
     */
    private Long roleId;


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