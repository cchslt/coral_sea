package com.fnd.psi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.mapper.PsiInWarehouseUserRelationMapper;
import com.fnd.psi.model.PsiInWarehouseUserRelation;
import com.fnd.psi.service.PsiInWarehouseUserRelationService;
import com.fnd.psi.utils.ResultUtils;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author: chenchaohai
 * @Date: 2022-11-25 10:37
 * @Desc:
 * @See:
 */
@Slf4j
@AllArgsConstructor
@Service("psiInWarehouseUserRelationService")
public class PsiInWarehouseUserRelationServiceImpl extends ServiceImpl<PsiInWarehouseUserRelationMapper, PsiInWarehouseUserRelation> implements PsiInWarehouseUserRelationService {

    private ResultUtils resultUtils;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultVo addPsiInWarehouseUserRelationAndDeleteOld(Long userId, List<Long> inWarehouseIds) {
        lambdaUpdate().set(PsiInWarehouseUserRelation::getIsDeleted, 1)
                .eq(PsiInWarehouseUserRelation::getUserId,userId)
                .update();
        return addPsiInWarehouseUserRelation(userId,inWarehouseIds);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultVo addPsiInWarehouseUserRelation(Long userId, List<Long> inWarehouseIds) {
        final Date now = new Date();
        final List<PsiInWarehouseUserRelation> psiInWarehouseUserRelationList = Lists.newArrayList();

        inWarehouseIds.forEach(x ->{
            PsiInWarehouseUserRelation psiInWarehouseUserRelation = new PsiInWarehouseUserRelation();
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

        this.saveBatch(psiInWarehouseUserRelationList);

        return resultUtils.returnSuccess(psiInWarehouseUserRelationList);
    }

    @Override
    public List<PsiInWarehouseUserRelation> getWarehouseIdByUserIds(Set<Long> userIds) {
        final List<PsiInWarehouseUserRelation> warehouseUserRelationList =
                list(new LambdaQueryWrapper<PsiInWarehouseUserRelation>().in(PsiInWarehouseUserRelation::getUserId, userIds).
                eq(PsiInWarehouseUserRelation::getIsDeleted, false));
        return warehouseUserRelationList;
    }

    @Override
    public List<PsiInWarehouseUserRelation> getWarehouseIdByUserId(Long userId) {
        final List<PsiInWarehouseUserRelation> warehouseUserRelationList =
                list(new LambdaQueryWrapper<PsiInWarehouseUserRelation>().eq(PsiInWarehouseUserRelation::getUserId, userId).
                        eq(PsiInWarehouseUserRelation::getIsDeleted, false));
        return warehouseUserRelationList;
    }
}
