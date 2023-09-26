package com.fnd.psi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 15:45
 * @Desc:
 * @See:
 */
@Data
public class PsiTransferringOrderQuery extends BasePageQuery {


    @ApiModelProperty("调拨单编码")
    private String transferCode;

    @ApiModelProperty("warehouseId")
    private Long warehouseId;

    @ApiModelProperty("skuid")
    private Long productSkuId;

    @ApiModelProperty("产品名称")
    private String productSkuName;


    @ApiModelProperty("调拨单状态")
    private Integer transferringStatus;

}
