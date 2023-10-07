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
public class PsiTransferringOrderDTO  extends BaseDTO {

    /**
     * 调拨单编码
     */
    @ApiModelProperty("调拨单编码")
    private String transferCode;

    /**
     * 调拨源仓库id
     */
    @ApiModelProperty("调拨源仓库id")
    @NotNull(message = "调拨源仓库不能为空")
    private Long sourceWarehouseId;

    @ApiModelProperty("调拨源仓库名称")
    private String sourceWarehouseName;
//    /**
//     * 调拨目标仓库id
//     */
//    @ApiModelProperty("调拨目标仓库id")
//    private Long targetWarehouseId;
    /**
     * skuid
     */
    @ApiModelProperty("skuid")
    @NotNull(message = "调拨skuId不能为空")
    private Long productSkuId;
    /**
     * sku编码
     */
    @ApiModelProperty("sku编码")
    @NotBlank(message = "产品编码不能为空")
    private String productSkuCode;
    /**
     * 冗余 商品名称
     */
    @ApiModelProperty("冗余 商品名称")
    @NotBlank(message = "商品名称不能为空")
    private String productSkuName;
    /**
     * 商品数量
     */
    @ApiModelProperty("商品数量")
    @NotNull(message = "商品数量不能为空")
    @Min(value = 1, message = "商品数量必填")
    private Integer productCount;
    /**
     * 已入库数量
     */
    @ApiModelProperty("已入库数量")
    private Integer receivedStorageCount;
    /**
     * 未入库数量
     */
    @ApiModelProperty("未入库数量")
    private Integer notYetStorageCount;
    /**
     * 调拨单状态
     */
    @ApiModelProperty("调拨单状态")
    private Integer transferringStatus;
    /**
     * 入库状态，1未入库、2部分入库、3全部入库、0已经关闭
     */
    @ApiModelProperty("入库状态，1未入库、2部分入库、3全部入库、0已经关闭")
    private Integer relationStorageStatus;
    /**
     * 数据类型：1:调拨， 2: 销售
     */
    @ApiModelProperty("数据类型：1:调拨入库， 2: 销售")
    private Integer addType;
    /**
     * 所属psi用户id
     */
    @ApiModelProperty("所属psi用户id")
    private Long belongUserId;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;
    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private Long createBy;

    @ApiModelProperty("创建人-名称")
    private String createByName;
    /**
     * 最后修改人
     */
    @ApiModelProperty("最后修改人")
    private Long updateBy;

    @ApiModelProperty("最后修改人")
    private String updateByName;
}
