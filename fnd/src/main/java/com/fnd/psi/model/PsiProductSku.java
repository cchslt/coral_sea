package com.fnd.psi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 10:39
 * @Desc:
 * @See:
 */
@TableName(value="t_psi_product_sku")
@Data
public class PsiProductSku {

    /**
     * 主键 默认主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)

    /**
     *  sku表id
     *
     */
    private Long id;


    /**
     *  sku编码
     *
     */
    private String skuCode;


    /**
     *  sku商品称
     *
     */
    private String skuProductName;


    /**
     *  创建userid
     *
     */
    private Long createUserId;


    /**
     *  修改userid
     *
     */
    private Long modifyUserId;


    /**
     *  是否删除0:未删除，1删除
     *
     */
    private Integer isDeleted;


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

}
