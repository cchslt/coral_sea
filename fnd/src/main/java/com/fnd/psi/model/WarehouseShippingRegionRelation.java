package com.fnd.psi.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fnd.psi.dto.BaseDomain;
import lombok.Data;

/**
 * @Desc: 仓库与发货区域关联表 实体映射类 带通用继承 注意此实体不需要再定义 id、gmtCreate、gmtModified
 * @See:
 */
@TableName(value="t_warehouse_shipping_region_relation")
@Data
public class WarehouseShippingRegionRelation extends BaseDomain {


    /**
     *  仓库id
     */
    private Long warehouseId;
    /**
     *  关联egatee_district_id
     */
    private Long districtId;
    /**
     *  三级区域id
     */
    private Long areaId;
    /**
     *  冗余 三级区域名称
     */
    private String areaName;
    /**
     *  创建人
     */
    private Long createBy;
    /**
     *  最后修改人
     */
    private Long updateBy;

}