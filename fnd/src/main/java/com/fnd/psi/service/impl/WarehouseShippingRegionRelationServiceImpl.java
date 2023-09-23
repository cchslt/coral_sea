package com.fnd.psi.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.constant.CommonConstant;
import com.fnd.psi.mapper.WarehouseShippingRegionRelationMapper;
import com.fnd.psi.model.WarehouseShippingRegionRelation;
import com.fnd.psi.service.WarehouseShippingRegionRelationService;
import com.fnd.psi.utils.PSIBaseUtils;
import com.fnd.psi.utils.ResultUtils;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: skipper
 * @Date: 2022-02-18
 * @Desc: 仓库与发货区域关联表 业务实现
 * @See:
 */
@Slf4j
@Service("warehouseShippingRegionRelationService")
@AllArgsConstructor
public class WarehouseShippingRegionRelationServiceImpl extends ServiceImpl<WarehouseShippingRegionRelationMapper, WarehouseShippingRegionRelation> implements WarehouseShippingRegionRelationService {


    private ResultUtils resultUtils;

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public String addWarehouseShippingRegionRelation(Long warehouseInfoId, List<Long> areaIdList) {
        final List<WarehouseShippingRegionRelation> WarehouseShippingRegionRelations = Lists.newArrayList();
        if (PSIBaseUtils.objParametersIsBlank(warehouseInfoId, areaIdList)) {
            return null;
        }

        List<String> stringListAreaName = new ArrayList<>();
        /**
         * 这个数据量到时会很大  修改成批量的
         */
//        this.saveBatch(WarehouseShippingRegionRelations);
        return Joiner.on(CommonConstant.COMMA_EN).join(stringListAreaName);

    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public String updateWarehouseShippingRegionRelation(Long warehouseInfoId, List<Long> areaIdList) {
        if (PSIBaseUtils.objParametersIsBlank(warehouseInfoId, areaIdList)) {
            return null;
        }
        //查出已绑定的区域关系
        List<WarehouseShippingRegionRelation> warehouseShippingRegionRelationList  =
                baseMapper.selectList(new LambdaQueryWrapper<WarehouseShippingRegionRelation>()
                        .eq(WarehouseShippingRegionRelation::getWarehouseId,warehouseInfoId)
                .eq(WarehouseShippingRegionRelation::getIsDeleted,CommonConstant.IS_DELETED_FALSE));
        if(CollectionUtil.isNotEmpty(warehouseShippingRegionRelationList)){
            this.lambdaUpdate()
                    .set(WarehouseShippingRegionRelation::getIsDeleted, 1)
                    .eq(WarehouseShippingRegionRelation::getWarehouseId,warehouseInfoId)
                    .update();
        }

        List<String> stringListAreaName = new ArrayList<>();

        return Joiner.on(CommonConstant.COMMA_EN).join(stringListAreaName);
    }

    @Override
    public List<Long> getAreaIdListByWarehouseId(Long warehouseId){
        List<WarehouseShippingRegionRelation> warehouseShippingRegionRelationList =
                list(new LambdaQueryWrapper<WarehouseShippingRegionRelation>().eq(WarehouseShippingRegionRelation::getWarehouseId,warehouseId)
                .eq(WarehouseShippingRegionRelation::getIsDeleted, CommonConstant.IS_DELETED_FALSE));
        List<Long> longList = new ArrayList<>();
        if(CollUtil.isNotEmpty(warehouseShippingRegionRelationList)){
            warehouseShippingRegionRelationList.forEach(warehouseShippingRegionRelation -> {
                longList.add(warehouseShippingRegionRelation.getAreaId());
            });
        }
        return longList;
    }
}
