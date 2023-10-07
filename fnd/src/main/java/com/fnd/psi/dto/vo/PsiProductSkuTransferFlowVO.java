package com.fnd.psi.dto.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fnd.psi.dto.BasePageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 10:40
 * @Desc:
 * @See:
 */
@Data
public class PsiProductSkuTransferFlowVO {

    /**
     * sku编码
     */
    @ApiModelProperty("sku编码")
    private String skuCode;

    @ApiModelProperty("sku名称")
    private String productSkuName;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty("调拨/销售时间")
    private Date transferDate;


    private Long createBy;

    @ApiModelProperty("操作人")
    private String createUserName;

    @ApiModelProperty("操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;

    /**
     * 调拨仓库
     */
    private Long sourceWarehouseId;



    @ApiModelProperty("调出仓库")
    private String sourceWarehouseName;


    /**
     * 目的仓库
     *
     */
    private Long warehouseId;

    @ApiModelProperty("调入仓库")
    private String targetWarehouseName;

    @ApiModelProperty("调拨/销售数量")
    private Integer productCount;


    @ApiModelProperty("备注")
    private String remarks;
}
