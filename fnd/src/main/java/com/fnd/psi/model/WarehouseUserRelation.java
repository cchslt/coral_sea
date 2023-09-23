package com.fnd.psi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: skipper
 * @Date: 2022-02-10
 * @Desc: 卖家仓库关系配置表 实体映射类
 * @See:
 */
@TableName(value="t_warehouse_user_relation")
@Data
public class WarehouseUserRelation implements Serializable {

    /**
     * 主键 默认主键自增
     */
    @TableId(value = "id", type = IdType.AUTO)

    /**
     *  自增主键
     *
     */
    private Long id;


    /**
     *  仓库id
     *
     */
    private Long warehouseId;


    /**
     *  psi用户id
     *
     */
    private Long userId;


    /**
     *  关联关系类型 1:强关联
     *
     */
    private Integer relationType;


    /**
     *  仓库类型
     *
     */
    private Integer warehouseType;


    /**
     *  合同类型
     *
     */
    private Integer contractType;


    /**
     *  是否删除0:未删除，1删除
     *
     */
    private Integer isDeleted;


    /**
     *  创建时间
     *
     */
    private Date gmtCreate;


    /**
     *  更新时间
     *
     */
    private Date gmtModified;


}