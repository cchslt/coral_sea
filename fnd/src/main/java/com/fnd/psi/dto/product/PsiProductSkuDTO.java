package com.fnd.psi.dto.product;

import com.fnd.psi.dto.BaseDTO;
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
public class PsiProductSkuDTO extends BaseDTO {

    /**
     * sku编码
     */
    @NotBlank(message = "产品编号不能为空")
    @ApiModelProperty("sku编码")
    private String skuCode;
    /**
     * sku商品称
     */
    @ApiModelProperty("sku商品称")
    @NotBlank(message = "商品名称不能为空")
    private String skuProductName;
    /**
     * 创建userid
     */
    @ApiModelProperty("创建userid")
    private Long createUserId;
    /**
     * 修改userid
     */
    @ApiModelProperty("修改userid")
    private Long modifyUserId;


}