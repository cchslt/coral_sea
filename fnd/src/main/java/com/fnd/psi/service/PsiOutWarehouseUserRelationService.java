package com.fnd.psi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.model.PsiOutWarehouseUserRelation;

import java.util.List;
import java.util.Set;


public interface PsiOutWarehouseUserRelationService extends IService<PsiOutWarehouseUserRelation> {


    /**
     * 删除老的新增新的
     * @param userId
     * @param warehouseIds
     */
    void addPsiOutWarehouseUserRelationAndDeleteOld(Long userId, List<Long> warehouseIds) ;

    /**
     * 直接新增新的
     * @param userId
     * @param outWarehouseIds
     */
    void addPsiOutWarehouseUserRelation(Long userId, List<Long> outWarehouseIds);

    /***
     *  查询权限的集合
     * @param userIds
     * @return
     */
    List<PsiOutWarehouseUserRelation> getWarehouseIdByUserIds(Set<Long> userIds);

    /**
     * 查询权限的集合
     * @param userId
     * @return
     */
    List<PsiOutWarehouseUserRelation> getWarehouseIdByUserId(Long userId);
}