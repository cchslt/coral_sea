package com.fnd.psi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fnd.psi.dto.vo.WarehouseUserRelationInfoDTO;
import com.fnd.psi.model.WarehouseUserRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @Desc:  卖家仓库关系配置表 Mapper
 * @See:
 */
public interface WarehouseUserRelationMapper extends BaseMapper<WarehouseUserRelation> {


    List<WarehouseUserRelationInfoDTO> getWarehouseInfoByUserIds(@Param("userIds") Set<Long> userIds);

}
