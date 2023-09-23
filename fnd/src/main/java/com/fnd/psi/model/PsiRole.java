package com.fnd.psi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xu_xin
 * @Date: 2022/1/26/026 17:27
 */
@TableName(value="t_psi_role")
@Data
public class PsiRole implements Serializable {

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
     *  角色名字
     *
     */
    private String roleName;


    /**
     *  所属用户
     *
     */
    private Long belongUserId;


    /**
     *  所属供应商
     *
     */
    private Long belongSupplierId;


    /**
     *  状态：0禁用，1启用
     *
     */
    private Integer status;


    /**
     *  备注
     *
     */
    private String remark;


    /**
     *  国家id
     *
     */
    private Long countryId;


    /**
     *  国家code
     *
     */
    private String countryCode;


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
     *  创建人
     *
     */
    private Long createdUserId;


    /**
     *  是否被删除
     *
     */
    private Integer isDeleted;


}