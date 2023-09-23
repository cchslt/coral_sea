package com.fnd.psi.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CHAOHAI.CHEN
 * @date 2022/02/17 19:44
 * @description 仓库优先级VO
 */
@Data
public class WarehousePriorityVO {

    @ApiModelProperty("主键")
    private Long id;

    /**
     * 仓库名称
     */
    @ApiModelProperty("仓库名称")
    private String warehouseName;
}
