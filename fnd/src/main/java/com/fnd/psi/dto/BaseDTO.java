package com.fnd.psi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc: 通用继承的DTO  包含建表规范必备的三个参数 id、gmtCreate、gmtModified
 * @See:
 */
@Getter
@Setter
public class BaseDTO implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date gmtCreate;


    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date gmtModified;

    /**
     * 是否删除0:未删除，1删除
     */
    @ApiModelProperty("是否删除0:未删除，1删除")
    private Integer isDeleted;

}
