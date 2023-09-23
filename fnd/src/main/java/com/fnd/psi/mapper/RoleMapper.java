package com.fnd.psi.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fnd.psi.dto.vo.PsiUserRoleVO;
import com.fnd.psi.model.PsiRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @Author: xu_xin
 * @Date: 2022/1/26/026 17:27
 */
public interface RoleMapper extends BaseMapper<PsiRole> {


    List<PsiUserRoleVO> getRoleInfoByUserId(@Param("userIds") Set<Long> userIds);

}
