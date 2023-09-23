package com.fnd.psi.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fnd.psi.dto.BaseDomain;
import lombok.Data;

/**
 * @Desc: 仓库主表 实体映射类 带通用继承 注意此实体不需要再定义 id、gmtCreate、gmtModified
 * @See:
 */
@TableName(value="t_warehouse_info")
@Data
public class WarehouseInfo extends BaseDomain {

    /**
     *  自增主键
     */
    private Long belongUserId;
    /**
     *  仓库编码
     */
    private String warehouseCode;
    /**
     *  仓库名称
     */
    private String warehouseName;
    /**
     *  仓库联系人
     */
    private String warehouseContact;
    /**
     *  仓库联系电话
     */
    private String warehousePhone;
    /**
     *  仓库联系邮箱
     */
    private String warehouseEmail;
    /**
     *  仓库类型 1：egatee自动创建仓，2，egatee自营仓，3:托管仓库，4:第三方仓库
     */
    private Integer warehouseType;
    /**
     *  仓库状态 1:启用，2:停用
     */
    private Integer warehouseStatus;
    /**
     *  仓库区域
     */
    private String warehouseArea;
    /**
     *  仓库详细地址
     */
    private String warehouseAddress;
    /**
     *  仓库面积 单位平方米
     */
    private Integer warehouseAcreage;
    /**
     *  冗余 发货区域
     */
    private String shippingRegion;
    /**
     *  库存优先级
     */
    private Integer inventoryPriority;
    /**
     *  所属国家id
     */
    private Long countryId;
    /**
     *  国家code
     *
     */
    private String countryCode;
    /**
     *  仓库备注
     */
    private String warehouseRemark;
    /**
     *  创建人
     */
    private Long createBy;
    /**
     *  最后修改人
     */
    private Long updateBy;
    /**
     *  是否默认仓库 0 否 1 是
     */
    private Boolean isDefault;
    /**
     *  是否虚拟仓库 0 否 1 是
     */
    private Boolean isVirtual;

}