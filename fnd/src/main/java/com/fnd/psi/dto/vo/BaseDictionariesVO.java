package com.fnd.psi.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Skipper
 * @Date: 2022/2/18/018 21:11
 * @Desc: 通用字典VO   id ： name
 * @See:
 */
@Data
public class BaseDictionariesVO {


    @ApiModelProperty("id")
    /**
     * id
     */
    private Long id;

    private String code;

    @ApiModelProperty("名称")
    /**
     * 名称
     */
    private String name;


}
