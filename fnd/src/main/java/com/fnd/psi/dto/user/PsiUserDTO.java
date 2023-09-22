package com.fnd.psi.dto.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xu_xin
 * @Date: 2022/2/28/028 16:16
 */
@Data
public class PsiUserDTO implements Serializable {

    /**
     *  主键
     *
     */
    private Long id;


    /**
     *  用户名(登录名称)
     *
     */
    private String userName;


    /**
     *  昵称
     *
     */
    private String nickName;


    /**
     *  用户类型（0是超级管理 1 商家管理员）
     *
     */
    private Long userType;


    /**
     *  邮箱
     *
     */
    private String email;



    /**
     * 账户级别:0.最高
     */

    private Integer level;

    /**
     *  手机号
     *
     */
    private String phone;


    /**
     *  是否可用
     *
     */
    private Integer userStatus;

    /**
     *  所属用户
     */
    private Long belongUserId;
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
    private Date gmtCreate;


    /**
     *  更新时间
     *
     */
    private Date gmtModified;


    /**
     *  是否删除
     *
     */
    private Boolean isDeleted;

    /**
     *关联的国包/二级(DBR)客户代码
     */

    private String customerCode;

    /**
     *关联的国包/二级(DBR)客户名称
     */
    private String customerName;

    private Integer sourceType;

    private String sourceId;

    private Long createUserId;

}
