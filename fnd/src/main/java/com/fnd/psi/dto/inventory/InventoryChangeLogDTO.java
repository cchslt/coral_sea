package com.fnd.psi.dto.inventory;

import com.fnd.psi.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-26 11:18
 * @Desc:
 * @See:
 */
@Data
public class InventoryChangeLogDTO extends BaseDTO {

    /**
     * sku id
     */
    @ApiModelProperty("sku id")
    private Long productSkuId;
    /**
     * warehouse_id
     */
    @ApiModelProperty("warehouse_id")
    private Long warehouseId;
    /**
     * 库存变更类型:1:增加，2:减少
     */
    @ApiModelProperty("库存变更类型:1:增加，2:减少")
    private Integer changeType;
    /**
     * 变更库存类型:1:可售库存，2:可用，3待入库，4占用
     */
    @ApiModelProperty("变更库存类型:1:可售库存，2:可用，3待入库，4占用")
    private Integer inventoryType;
    /**
     * 变更数量
     */
    @ApiModelProperty("变更数量")
    private Integer changeQuantity;
    /**
     * 变更源编码，eg:order_code
     */
    @ApiModelProperty("变更源编码，eg:order_code")
    private String changeSourceCode;
    /**
     * 变更占用备注
     */
    @ApiModelProperty("变更占用备注")
    private String changeRemark;
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