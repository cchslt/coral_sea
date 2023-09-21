package com.fnd.psi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Desc: 提供通用分页DTO包装  目的是规范统一传参回参
 * @See:
 */
@Data
public class PageDTO<T> implements Serializable {
    /**
     * 每页数量
     */
    @ApiModelProperty("每页数量")
    private Long size;
    /**
     * 页码
     */
    @ApiModelProperty("页码")
    private Long current;
    /**
     * 数据总数
     */
    @ApiModelProperty("数据总数")
    private Long total;
    /**
     * 数据
     */
    @ApiModelProperty("数据")
    private List<T> records;

    /**
     * 自动优化 countSql
     */
    @ApiModelProperty("自动优化 countSql ")
    private Boolean optimizeCountSql;
    /**
     * 搜索数量
     */
    @ApiModelProperty("搜索数量")
    private Boolean searchCount;
    /**
     *
     */
    private String countId;
    /**
     * 最大limit
     */
    @ApiModelProperty("最大limit")
    private Long maxLimit;

    @ApiModelProperty("总页数")
    private Integer pages;

}
