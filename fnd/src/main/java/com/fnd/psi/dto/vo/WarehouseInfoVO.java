package com.fnd.psi.dto.vo;

import com.fnd.psi.annotation.TransitionEnum;
import com.fnd.psi.constant.WarehouseStatusEnum;
import com.fnd.psi.constant.WarehouseTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Skipper
 * @Date: 2022/2/15/015 21:55
 * @Desc:
 * @See:
 */
@Data
public class WarehouseInfoVO implements Serializable {

    @ApiModelProperty("主键")
    private Long id;
    /**
     * 仓库编码
     */
    @ApiModelProperty("仓库编码")
    private String warehouseCode;
    /**
     * 仓库名称
     */
    @ApiModelProperty("仓库名称")
    private String warehouseName;
    /**
     * 仓库联系人
     */
    @ApiModelProperty("仓库联系人")
    private String warehouseContacter;
    /**
     * 仓库联系电话
     */
    @ApiModelProperty("仓库联系电话")
    private String warehousePhone;


    @ApiModelProperty(value = "仓库类型 1：egatee自动创建仓，2,egatee自营仓,3:托管仓库,4:第三方仓库",dataType = "String")
    @TransitionEnum(enumClass = WarehouseTypeEnum.class,suffix = "desc")
    private Integer warehouseType = 4;
    /**
     * 仓库详细地址
     */
    @ApiModelProperty("仓库详细地址")
    private String warehouseAddress;


    @ApiModelProperty("冗余 发货区域")
    private String shippingRegion;

//    @ApiModelProperty("发货区域 id集合")
//    private List<Long> shippingRegionIds;

    @ApiModelProperty("发货区域-3级区域数量")
    private Integer shippingRegionCnt;

    /**
     * 库存优先级
     */
    @ApiModelProperty("库存优先级")
    private Integer inventoryPriority;

    /**
     * 仓库状态
     */
    @ApiModelProperty("仓库状态")
    @TransitionEnum(enumClass = WarehouseStatusEnum.class,suffix = "EnumName")
    private Integer warehouseStatus;
    /**
     * 仓库备注
     */
    @ApiModelProperty("仓库备注")
    private String warehouseRemark;

    @ApiModelProperty("国家id")
    private Long countryId;
    /**
     *  国家code
     *
     */
    private String countryCode;

    @ApiModelProperty("归属用户")
    private Long belongUserId;
    @ApiModelProperty("商家名称")
    private String merchantName;

}
