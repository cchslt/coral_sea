package com.fnd.psi.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 10:40
 * @Desc:
 * @See:
 */
@Data
public class PsiProductSkuVO {

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



}