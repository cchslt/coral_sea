package com.fnd.psi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 15:45
 * @Desc:
 * @See:
 */
@Data
public class PsiTransferringOrderUpdateDTO extends BaseDTO {

//    /**
//     * 主键
//     */
//    @ApiModelProperty("主键")
//    @NotNull(message = "id不能为空")
//    private Long id;

    /**
     * skuid
     */
    @ApiModelProperty("skuId")
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    /**
     * 调拨目标仓库id
     */
    @ApiModelProperty("调拨仓库id")
    @NotNull(message = "调拨源仓库不能为空")
    private Long sourceWarehouseId;

    /**
     * 调拨目标仓库id
     */
    @ApiModelProperty("调拨目标仓库id")
//    @NotNull(message = "调拨目标仓库不能为空")
    private Long targetWarehouseId;

    /**
     * 商品数量
     */
    @ApiModelProperty("商品数量")
    @NotNull(message = "商品数量不能为空")
    @Min(value = 1, message = "商品数量必填")
    private Integer productCount;

    /**
     * 调拨单状态
     */
    @ApiModelProperty("调拨单状态")
    private Integer transferringStatus;

    /**
     * 数据类型：1:调拨， 2: 销售
     */
    @ApiModelProperty("数据类型：1:调拨， 2: 销售")
    @NotNull(message = "数据类型不能为空")
    private Integer addType;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;

}
