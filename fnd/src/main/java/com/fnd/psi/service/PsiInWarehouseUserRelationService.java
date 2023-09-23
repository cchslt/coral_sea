package com.fnd.psi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.model.PsiInWarehouseUserRelation;

import java.util.List;
import java.util.Set;

/**
 * @Author: chenchaohai
 * @Date: 2022-11-25 10:37
 * @Desc:
 * @See:
 */
public interface PsiInWarehouseUserRelationService extends IService<PsiInWarehouseUserRelation> {

    /**
     * 更新调入仓库权限-先删除已存在的
     * @param userId
     * @param inWarehouseIds
     */
    ResultVo addPsiInWarehouseUserRelationAndDeleteOld(Long userId, List<Long> inWarehouseIds);

    /**
     * 新增调入仓库权限
     * @param userId
     * @param inWarehouseIds
     * @return
     */
    ResultVo addPsiInWarehouseUserRelation(Long userId, List<Long> inWarehouseIds);


    /***
     *  查询权限的集合
     * @param userIds
     * @return
     */
    List<PsiInWarehouseUserRelation> getWarehouseIdByUserIds(Set<Long> userIds);

    /**
     * 查询权限的集合
     * @param userId
     * @return
     */
    List<PsiInWarehouseUserRelation> getWarehouseIdByUserId(Long userId);
}
