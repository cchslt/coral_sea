package com.fnd.psi.dto.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class RoleListDTO {

    @JsonProperty("current")
    @ApiModelProperty("当前页码")
    private int current;

    @JsonProperty("pageSize")
    @ApiModelProperty("页大小")
    private int pageSize;
}
