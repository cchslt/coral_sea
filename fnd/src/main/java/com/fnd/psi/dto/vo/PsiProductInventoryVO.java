package com.fnd.psi.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-27 11:13
 * @Desc:
 * @See:
 */
@Data
public class PsiProductInventoryVO {

    @ApiModelProperty("库存归属 sku id")
    private Long productSkuId;

    /**
     * sku编码
     */
    @ApiModelProperty("sku编码")
    private String productSkuCode;

    /**
     * sku商品称
     */
    @ApiModelProperty("sku商品称")
    private String productSkuName;


    @ApiModelProperty("可售库存")
    private Integer sellableQuantity;
}
