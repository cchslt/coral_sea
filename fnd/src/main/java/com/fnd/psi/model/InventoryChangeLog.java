package com.fnd.psi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-26 11:17
 * @Desc:
 * @See:
 */
@TableName(value="t_inventory_change_log")
@Data
public class InventoryChangeLog implements Serializable {

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
     *  sku id
     *
     */
    private Long productSkuId;


    /**
     *  warehouse_id
     *
     */
    private Long warehouseId;


    /**
     *  库存变更类型:1:增加，2:减少
     *
     */
    private Integer changeType;


    /**
     *  变更库存类型:1:可售库存，2:可用，3待入库，4占用
     *
     */
    private Integer inventoryType;


    /**
     *  变更数量
     *
     */
    private Integer changeQuantity;


    /**
     *  变更源编码，eg:order_code
     *
     */
    private String changeSourceCode;


    /**
     *  变更占用备注
     *
     */
    private String changeRemark;


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