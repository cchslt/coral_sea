package com.fnd.psi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 15:45
 * @Desc:
 * @See:
 */
@Data
public class PsiTransferringOrderUpdateStatusDetailDTO {

    /**
     *  归属id
     */
    private Long transferringOrderId;
    /**
     * 调拨单状态
     */
    @ApiModelProperty("调拨单状态")
    @NotNull(message = "调拨单状态不能为空")
    private Integer transferringStatus;

    /**
     * 商品数量
     */
    @ApiModelProperty("商品数量")
    @NotNull(message = "商品数量不能为空")
    private Integer productCount;
}
