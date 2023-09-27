package com.fnd.psi.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-27 11:06
 * @Desc:
 * @See:
 */
@Data
public class PsiInventoryVo {

    @ApiModelProperty("商品id")
    private Long productSkuId;

    @ApiModelProperty("仓库id")
    private Long warehouseId;


}
