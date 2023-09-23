package com.fnd.psi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.dto.vo.WarehouseUserRelationInfoDTO;
import com.fnd.psi.mapper.WarehouseUserRelationMapper;
import com.fnd.psi.model.WarehouseUserRelation;
import com.fnd.psi.service.WarehouseUserRelationService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * @Author: skipper
 * @Date: 2022-02-10
 * @Desc: 卖家仓库关系配置表 业务实现
 * @See:
 */
@Slf4j
@Service("warehouseUserRelationService")
public class WarehouseUserRelationServiceImpl extends ServiceImpl<WarehouseUserRelationMapper, WarehouseUserRelation> implements WarehouseUserRelationService {


    @Autowired
    private WarehouseUserRelationMapper warehouseUserRelationMapper;

    @Override
    @Transactional
    public void userWarehouseRelation(Long userId, List<Long> warehouseIds) {

        lambdaUpdate().set(WarehouseUserRelation::getIsDeleted, 1)
                .eq(WarehouseUserRelation::getUserId,userId)
                .update();
        addUserWarehouseRelation(userId,warehouseIds);
    }

    @Override
    public void addUserWarehouseRelation(Long userId, List<Long> warehouseIds){
        final Date date = new Date();
        final List<WarehouseUserRelation> warehouseUserRelations = Lists.newArrayList();
        warehouseIds.forEach(x ->{
            WarehouseUserRelation warehouseUserRelation = new WarehouseUserRelation();
            warehouseUserRelation.setRelationType(1);
            warehouseUserRelation.setWarehouseId(x);
            warehouseUserRelation.setWarehouseType(0);
            warehouseUserRelation.setRelationType(0);
            warehouseUserRelation.setContractType(0);
            warehouseUserRelation.setUserId(userId);
            warehouseUserRelation.setGmtCreate(date);
            warehouseUserRelation.setGmtModified(date);
            warehouseUserRelation.setIsDeleted(0);
            warehouseUserRelations.add(warehouseUserRelation);
        });
        saveBatch(warehouseUserRelations);
    }

    @Override
    public List<WarehouseUserRelation> getWarehouseIdByUserIds(Set<Long> userIds) {
        final List<WarehouseUserRelation> warehouseUserRelationList = list(new LambdaQueryWrapper<WarehouseUserRelation>().in(WarehouseUserRelation::getUserId, userIds).
                eq(WarehouseUserRelation::getIsDeleted, false));
        return warehouseUserRelationList;
    }

    @Override
    public List<WarehouseUserRelationInfoDTO> getWarehouseInfoByUserIds(Set<Long> userIds) {
        return warehouseUserRelationMapper.getWarehouseInfoByUserIds(userIds);
    }
}
