package com.fnd.psi.dto.vo;

import com.fnd.psi.dto.BasePageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 10:40
 * @Desc:
 * @See:
 */
@Data
public class PsiWarehouseRequestVO extends BasePageQuery {

    /**
     * 仓库名称
     */
    @ApiModelProperty("仓库名称")
    private String warehouseName;



}