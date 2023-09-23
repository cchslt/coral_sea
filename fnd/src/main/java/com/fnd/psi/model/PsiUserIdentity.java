package com.fnd.psi.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Date: 2022/2/18/018 16:48
 */
@TableName(value="t_psi_user_identity")
@Data
public class PsiUserIdentity implements Serializable {

    /**
     * 主键 默认主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)

    /**
     *  主键
     *
     */
    private Long id;


    /**
     *  (1、 国包 2、二级（dbr) 3、r)
     *
     */
    private Integer identityType;


    /**
     *  关联外部 id (国包 二级 存入的是 buyer_id )
     *
     */
    private Long sourceId;


    /**
     *  psi 用户id
     *
     */
    private Long userId;


    /**
     *  创建时间
     *
     */
    private Date gmtCreate;


    /**
     *  修改时间
     *
     */
    private Date gmtModified;


    /**
     *  是否被删除
     *
     */
    private Boolean isDeleted;


}