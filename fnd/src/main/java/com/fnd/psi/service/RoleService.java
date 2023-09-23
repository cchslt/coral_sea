package com.fnd.psi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.vo.PsiRoleVO;
import com.fnd.psi.dto.vo.PsiUserRoleVO;
import com.fnd.psi.dto.vo.RoleListDTO;
import com.fnd.psi.model.PsiRole;
import com.fnd.psi.model.PsiUser;

import java.util.List;
import java.util.Set;


public interface RoleService extends IService<PsiRole>{

    /**
     * 查询角色列表
     * @return
     */
    ResultVo<IPage<PsiRole>> findUserAllRole(RoleListDTO roleListDTO);

    /**
     * 添加修改角色
     * @param
     * @return
     */
    ResultVo saveRole(PsiRoleVO psiRoleVO) ;

    /**
     * 获取角色权限信息
     * @param roleId
     * @return
     */
    ResultVo<PsiRoleVO> findRoleById(Long roleId);

    /**
     * RoleId 获取角色信息
     * @param userRoleId
     * @return
     */
    List<PsiRole> getRoleInfoByUserRoleId(Set<Long> userRoleId);


    /**
     * 根据userId集合查询用户角色
     */
    List<PsiUserRoleVO> getRoleInfoByUserId(Set<Long> userIds);


    /**
     *  默认创建一个管理员
     * @return
     */
    PsiRole maxMerchantRole(PsiUser psiUser);
}