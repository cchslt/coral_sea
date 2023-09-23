package com.fnd.psi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.mapper.PsiOutWarehouseUserRelationMapper;
import com.fnd.psi.model.PsiOutWarehouseUserRelation;
import com.fnd.psi.service.PsiOutWarehouseUserRelationService;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author: xu_xin
 * @Date: 2022/11/11/011 10:16
 */
@Slf4j
@AllArgsConstructor
@Service("psiOutWarehouseUserRelationService")
public class PsiOutWarehouseUserRelationServiceImpl extends ServiceImpl<PsiOutWarehouseUserRelationMapper, PsiOutWarehouseUserRelation> implements PsiOutWarehouseUserRelationService {



    @Override
    @Transactional
    public void addPsiOutWarehouseUserRelationAndDeleteOld(Long userId, List<Long> warehouseIds) {

        lambdaUpdate().set(PsiOutWarehouseUserRelation::getIsDeleted, 1)
                .eq(PsiOutWarehouseUserRelation::getUserId,userId)
                .update();
        addPsiOutWarehouseUserRelation(userId,warehouseIds);
    }


    @Override
    public void addPsiOutWarehouseUserRelation(Long userId, List<Long> warehouseIds){
        final Date now = new Date();
        final List<PsiOutWarehouseUserRelation> psiInWarehouseUserRelationList = Lists.newArrayList();
        warehouseIds.forEach(x ->{
            PsiOutWarehouseUserRelation psiInWarehouseUserRelation = new PsiOutWarehouseUserRelation();
            psiInWarehouseUserRelation.setRelationType(1);
            psiInWarehouseUserRelation.setWarehouseId(x);
            psiInWarehouseUserRelation.setWarehouseType(0);
            psiInWarehouseUserRelation.setRelationType(0);
            psiInWarehouseUserRelation.setContractType(0);
            psiInWarehouseUserRelation.setUserId(userId);
            psiInWarehouseUserRelation.setGmtCreate(now);
            psiInWarehouseUserRelation.setGmtModified(now);
            psiInWarehouseUserRelation.setIsDeleted(0);
            psiInWarehouseUserRelationList.add(psiInWarehouseUserRelation);
        });
        saveBatch(psiInWarehouseUserRelationList);
    }

    @Override
    public List<PsiOutWarehouseUserRelation> getWarehouseIdByUserIds(Set<Long> userIds) {
        final List<PsiOutWarehouseUserRelation> warehouseUserRelationList = list(new LambdaQueryWrapper<PsiOutWarehouseUserRelation>().in(PsiOutWarehouseUserRelation::getUserId, userIds).
                eq(PsiOutWarehouseUserRelation::getIsDeleted, false));
        return warehouseUserRelationList;
    }

    @Override
    public List<PsiOutWarehouseUserRelation> getWarehouseIdByUserId(Long userId) {
        final List<PsiOutWarehouseUserRelation> warehouseUserRelationList = list(new LambdaQueryWrapper<PsiOutWarehouseUserRelation>().eq(PsiOutWarehouseUserRelation::getUserId, userId).
                eq(PsiOutWarehouseUserRelation::getIsDeleted, false));
        return warehouseUserRelationList;
    }


}
