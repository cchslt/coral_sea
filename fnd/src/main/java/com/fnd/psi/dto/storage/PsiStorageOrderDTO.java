package com.fnd.psi.dto.storage;

import com.fnd.psi.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-25 17:28
 * @Desc:
 * @See:
 */
@Data
public class PsiStorageOrderDTO extends BaseDTO {

    /**
     * 入库单单号
     */
    @ApiModelProperty("入库单单号")
    private String storageOrderCode;
    /**
     * 来源id
     */
    @ApiModelProperty("来源id")
    private Long sourceId;
    /**
     * 来源单号
     */
    @ApiModelProperty("来源单号")
    private String sourceCode;
    /**
     * 来源业务单据时间
     */
    @ApiModelProperty("来源业务单据时间")
    private Date sourceBusinessTime;

    @ApiModelProperty("所在仓库id")
    private Long sourceWarehouseId;

    /**
     * 所在仓库id
     */
    @ApiModelProperty("目的仓库id")
    private Long warehouseId;
    /**
     * 商品数量
     */
    @ApiModelProperty("商品数量")
    private Integer productCount;

    private Long productSkuId;


    private String productSkuCode;
    /**
     * 入库数量
     */
    @ApiModelProperty("入库数量")
    private Integer receivedCount;
    /**
     * 入库状态，1未入库、2部分入库、3全部入库、0已经关闭
     */
    @ApiModelProperty("入库状态，1未入库、2部分入库、3全部入库、0已经关闭")
    private Integer storageStatus;
    /**
     * 所属psi用户id
     */
    @ApiModelProperty("所属psi用户id")
    private Long belongUserId;
    /**
     * 单据备注
     */
    @ApiModelProperty("单据备注")
    private String remarks;
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

    @ApiModelProperty("数据类型：1:调拨， 2: 销售")
    private Integer addType;

}