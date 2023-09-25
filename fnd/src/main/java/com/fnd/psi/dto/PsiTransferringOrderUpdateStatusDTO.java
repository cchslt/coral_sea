package com.fnd.psi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 15:45
 * @Desc:
 * @See:
 */
@Data
public class PsiTransferringOrderUpdateStatusDTO extends BaseDTO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @NotNull(message = "id不能为空")
    private Long id;


    /**
     * 调拨单状态
     */
    @ApiModelProperty("调拨单状态")
    @NotNull(message = "调拨单状态不能为空")
    private Integer transferringStatus;


    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;

}
