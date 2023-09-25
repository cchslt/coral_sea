package com.fnd.psi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 14:48
 * @Desc:
 * @See:
 */
@TableName(value="t_psi_inventory")
@Data
public class PsiInventory implements Serializable {

    /**
     * 主键 默认主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)

    /**
     *  自增主键
     *
     */
    private Long id;


    /**
     *  库存归属 sku id
     *
     */
    private Long productSkuId;


    /**
     *  冗余 sku编码
     *
     */
    private String productSkuCode;


    /**
     *  库存所属 仓库id
     *
     */
    private Long warehouseId;


    /**
     *  可售库存(可用库存 - 占用库存)
     *
     */
    private Integer sellableQuantity;


    /**
     *  可用库存
     *
     */
    private Integer availableQuantity;


    /**
     *  在途库存
     *
     */
    private Integer inTransitQuantity;


    /**
     *  占用库存
     *
     */
    private Integer occupiedQuantity;


    /**
     *  是否删除0:未删除，1删除
     *
     */
    private Integer isDeleted;


    /**
     *  创建人
     *
     */
    private Long createBy;


    /**
     *  最后修改人
     *
     */
    private Long updateBy;


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