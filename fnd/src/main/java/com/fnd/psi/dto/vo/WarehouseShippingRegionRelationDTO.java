package com.fnd.psi.dto.vo;

import com.fnd.psi.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 *  @author skipper
 *  @date 2022-02-18
 *  @desc 仓库与发货区域关联表 DTO类 带通用继承 注意此实体不需要再定义 id、gmtCreate、gmtModified
 *  @see
 */
@Data
public class WarehouseShippingRegionRelationDTO extends BaseDTO {

    /**
     * 仓库id
     */
    @ApiModelProperty("仓库id")
    private Long warehouseId;
    /**
     * 关联egatee_district_id
     */
    @ApiModelProperty("关联egatee_district_id")
    private Long districtId;
    /**
     * 三级区域id
     */
    @ApiModelProperty("三级区域id")
    private Long areaId;
    /**
     * 冗余 三级区域名称
     */
    @ApiModelProperty("冗余 三级区域名称")
    private String areaName;
    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private Long createBy;
    /**
     * 最后修改人
     */
    @ApiModelProperty("最后修改人")
    private Long updateBy;
    /**
     * 是否删除0:未删除，1删除
     */
    @ApiModelProperty("是否删除0:未删除，1删除")
    private Integer isDeleted;


}