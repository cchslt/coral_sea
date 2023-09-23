package com.fnd.psi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.dto.vo.WarehouseUserRelationInfoDTO;
import com.fnd.psi.model.WarehouseUserRelation;

import java.util.List;
import java.util.Set;

/**
 * @Desc:  卖家仓库关系配置表 Service
 * @See:
 */
public interface WarehouseUserRelationService extends IService<WarehouseUserRelation>{


    /**
     * user新增仓库绑定
     * @param userId
     * @param warehouseIds
     */
    void addUserWarehouseRelation(Long userId, List<Long> warehouseIds);


    /**
     * user绑定仓库 会把之前的删除
     * @param userId
     * @param warehouseIds
     */
    void userWarehouseRelation(Long userId, List<Long> warehouseIds);

    /**
     * 查询 一批用户的仓库关联信息
     * @param userIds
     * @return
     */
    List<WarehouseUserRelation> getWarehouseIdByUserIds(Set<Long> userIds);

    /**
     * 查询仓库信息
     * @param userIds
     * @return
     */
    List<WarehouseUserRelationInfoDTO> getWarehouseInfoByUserIds(Set<Long> userIds);
}
