package com.fnd.psi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: chenchaohai
 * @Date: 2023-10-09 14:00
 * @Desc:
 * @See:
 */
@Data
public class PsiTransferringOrderStatusDTO extends BaseDTO{
    /**
     * 归属id
     */
    @ApiModelProperty("归属id")
    private Long transferringOrderId;
    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private Integer transferringStatus;
    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private Integer productCount;
    /**
     * 是否删除0:未删除，1删除
     */
    @ApiModelProperty("是否删除0:未删除，1删除")
    private Integer isDeleted;
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
