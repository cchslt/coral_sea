package com.fnd.psi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.model.WarehouseShippingRegionRelation;

import java.util.List;

/**
 * @Author: skipper
 * @Date:  2022-02-18
 * @Desc:  仓库与发货区域关联表 Service
 * @See:
 */
public interface WarehouseShippingRegionRelationService extends IService<WarehouseShippingRegionRelation>{

    /**
     *  新增仓库绑定区域关系 返回拼装好的区域串
     * @param warehouseInfoId
     * @param areaIdList
     * @return
     */
    String addWarehouseShippingRegionRelation(Long warehouseInfoId, List<Long> areaIdList);

    /**
     *  修改仓库绑定区域关系 返回拼装好的区域串
     * @param warehouseInfoId
     * @param areaIdList
     * @return
     */
    String updateWarehouseShippingRegionRelation(Long warehouseInfoId, List<Long> areaIdList);

    /**
     * 根据仓库ID获取绑定三级区域集合
     * @param warehouseId
     * @return
     */
    List<Long> getAreaIdListByWarehouseId(Long warehouseId);
}
