package com.fnd.psi.dto.inventory;

import com.fnd.psi.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 14:49
 * @Desc:
 * @See:
 */
@Data
public class PsiInventoryDTO extends BaseDTO {

    /**
     * 库存归属 sku id
     */
    @ApiModelProperty("库存归属 sku id")
    private Long productSkuId;
    /**
     * 冗余 sku编码
     */
    @ApiModelProperty("冗余 sku编码")
    private String productSkuCode;
    /**
     * 库存所属 仓库id
     */
    @ApiModelProperty("库存所属 仓库id")
    private Long warehouseId;
    /**
     * 可售库存(可用库存 - 占用库存)
     */
    @ApiModelProperty("可售库存(可用库存 - 占用库存)")
    private Integer sellableQuantity;
    /**
     * 可用库存
     */
    @ApiModelProperty("可用库存")
    private Integer availableQuantity;
    /**
     * 在途库存
     */
    @ApiModelProperty("在途库存")
    private Integer inTransitQuantity;
    /**
     * 占用库存
     */
    @ApiModelProperty("占用库存")
    private Integer occupiedQuantity;

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


}