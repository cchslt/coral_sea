package com.fnd.psi.dto.vo;

import com.fnd.psi.dto.BasePageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 10:40
 * @Desc:
 * @See:
 */
@Data
public class PsiProductSkuTransferFlowRequestVO extends BasePageQuery {

    /**
     * sku编码
     */
    @ApiModelProperty("sku编码")
    @NotNull(message = "商品编码不能为空")
    private String skuCode;



}