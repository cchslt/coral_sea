package com.fnd.psi.dto.vo;

import com.fnd.psi.dto.BasePageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 10:40
 * @Desc:
 * @See:
 */
@Data
public class PsiProductSkuVO extends BasePageQuery {

    /**
     * sku编码
     */
    @ApiModelProperty("sku编码")
    private String skuCode;

    /**
     * sku商品称
     */
    @ApiModelProperty("sku商品称")
    private String skuProductName;

    @ApiModelProperty("仓库id")
    private Long warehouseId;
}