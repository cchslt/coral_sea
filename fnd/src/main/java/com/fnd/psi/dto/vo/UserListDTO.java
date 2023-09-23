package com.fnd.psi.dto.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: xu_xin
 * @Date: 2022/2/14/014 15:18
 */
@Data
public class UserListDTO {

    @JsonProperty("current")
    @ApiModelProperty("当前页码")
    private int current;

    @JsonProperty("pageSize")
    @ApiModelProperty("页大小")
    private int pageSize;


    @ApiModelProperty("查询关键字")
    private String keywords;
}
