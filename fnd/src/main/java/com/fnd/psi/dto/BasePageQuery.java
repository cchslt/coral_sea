package com.fnd.psi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Desc: 通用列表查询传参
 * @See:
 */
@Data
@ApiModel
@Accessors(chain = true)
@ToString(callSuper = true)
public class BasePageQuery implements Serializable {
    @JsonProperty("current")
    @ApiModelProperty("当前页码")
    private Long pageNo;

    @JsonProperty("pageSize")
    @ApiModelProperty("页大小")
    private Long pageSize;

    @JsonProperty("keyword")
    @ApiModelProperty("搜索关键字")
    private String keyword;

    @ApiModelProperty("当前会话用户id")
    private Long userId;

    @ApiModelProperty("用于检索的Id")
    private Long idOffset;

}
