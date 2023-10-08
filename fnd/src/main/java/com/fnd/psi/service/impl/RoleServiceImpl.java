package com.fnd.psi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.user.PsiUserInfoDTO;
import com.fnd.psi.dto.vo.*;
import com.fnd.psi.mapper.PermissionMapper;
import com.fnd.psi.mapper.RoleMapper;
import com.fnd.psi.model.PsiRole;
import com.fnd.psi.model.PsiUser;
import com.fnd.psi.security.FndSecurityContextUtil;
import com.fnd.psi.service.PermissionService;
import com.fnd.psi.service.RolePermissionService;
import com.fnd.psi.service.RoleService;
import com.fnd.psi.utils.ResultVoUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@Service("roleService")
@AllArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, PsiRole> implements RoleService {

    private RoleMapper roleMapper;
    private PermissionService permissionService;
    private PermissionMapper permissionMapper;
    private RolePermissionService rolePermissionService;



    /**
     * 查询角色列表
     * @return
     */
    public ResultVo<IPage<PsiRole>> findUserAllRole(RoleListDTO roleListDTO) {
        final PsiUserInfoDTO currentUser = FndSecurityContextUtil.getContext().getPsiUserInfoDTO();
        IPage<PsiRole> page = new Page<>(roleListDTO.getCurrent(), roleListDTO.getPageSize());
        LambdaQueryWrapper<PsiRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PsiRole::getBelongUserId, currentUser.getUser().getBelongUserId());
        queryWrapper.eq(PsiRole::getIsDeleted, 0);
        queryWrapper.orderByDesc(PsiRole::getGmtCreate);
        IPage<PsiRole> recordPage = roleMapper.selectPage(page, queryWrapper);
        return ResultVoUtil.success(recordPage);
    }


    public ResultVo saveRole(PsiRoleVO psiRoleVO) {
        PsiRole psiRole = new PsiRole();
        BeanUtils.copyProperties(psiRoleVO,psiRole);
        final PsiUserInfoDTO currentUser = FndSecurityContextUtil.getContext().getPsiUserInfoDTO();
        if (Objects.isNull(psiRoleVO.getId())){
            Long belongUserId = currentUser.getUser().getBelongUserId();
            psiRole.setCountryCode(currentUser.getUser().getCountryCode());
            psiRole.setCountryId(currentUser.getUser().getCountryId());
            psiRole.setBelongUserId(belongUserId);
            psiRole.setCreatedUserId(currentUser.getUser().getId());
        }
        psiRole.setGmtModified(new Date());
        saveOrUpdate(psiRole);
        return ResultVoUtil.success();
    }


    public ResultVo<PsiRoleVO> findRoleById(Long roleId){
        PsiRoleVO roleVO = new PsiRoleVO();
        if (Objects.isNull(roleId)){
            return ResultVoUtil.error();
        }
        final PsiRole psiRole = getById(roleId);
        BeanUtils.copyProperties(psiRole,roleVO);

        final ResultVo<List<PermissionDTO>> listResultVo = permissionService.permissionList();
        List<PermissionDTO> listResultVoData = listResultVo.getData();
        final List<PermissionVO> permissionVOS = permissionMapper.listMenusByRoleId(psiRole.getId());

        final Set<Long> virtualPermissionIds = listResultVoData.stream().filter(x -> x.getVirtual()).map(PermissionDTO::getId).collect(Collectors.toSet());

        //最末节点的数据
        final Set<Long> parentSet = listResultVoData.stream().map(PermissionDTO::getParentId).collect(Collectors.toSet());
        final Set<Long> idSet = listResultVoData.stream().map(PermissionDTO::getId).collect(Collectors.toSet());
        final Set<Long> nonParentIdSet = idSet.stream().filter(x -> !parentSet.contains(x)).collect(Collectors.toSet());

        Set<Long> permissionSet = Sets.newHashSet();
        if (!CollectionUtils.isEmpty(permissionVOS)){
            permissionSet = permissionVOS.stream().filter(x -> x.getParentId() > 0 && nonParentIdSet.contains(x.getId())).map(PermissionVO::getId).collect(Collectors.toSet());
        }
        roleVO.setMenuData(permissionService.builderPermissionTree(listResultVoData.stream().filter(PermissionDTO::getShow).collect(Collectors.toList())));
        roleVO.setPermissionIds(Lists.newArrayList(permissionSet));
        roleVO.setVirtualPermissionIds(Lists.newArrayList(virtualPermissionIds));
        return ResultVoUtil.success(roleVO);
    }

    @Override
    public List<PsiRole> getRoleInfoByUserRoleId(Set<Long> userRoleId) {
        return list(new LambdaQueryWrapper<PsiRole>().in(PsiRole::getId, userRoleId).eq(PsiRole::getIsDeleted, 0));
    }

    @Override
    public List<PsiUserRoleVO> getRoleInfoByUserId(Set<Long> userIds) {
        return roleMapper.getRoleInfoByUserId(userIds);
    }




    /**
     * 查询默认的商家最大的角色
     * @return
     */
    @Override
    public PsiRole maxMerchantRole(PsiUser psiUser) {
        List<PsiRole> psiRoles = list(new LambdaQueryWrapper<PsiRole>().eq(PsiRole::getCreatedUserId, 0)
                .eq(PsiRole::getIsDeleted, 0).eq(PsiRole::getBelongUserId, psiUser.getBelongUserId()));
        if (CollectionUtils.isEmpty(psiRoles)){
            psiRoles = Lists.newArrayList();
            final List<PermissionDTO> rolePermission = permissionMapper.getRolePermission();
            if (!CollectionUtils.isEmpty(rolePermission)){
                final PsiRole psiRole = defaultRole(psiUser);
                save(psiRole);
                psiRoles.add(psiRole);
                UpdateRolePermissionDTO updateRolePermissionDTO = new UpdateRolePermissionDTO();
                updateRolePermissionDTO.setRoleId(psiRole.getId());
                updateRolePermissionDTO.setPermissionIds(rolePermission.stream().map(PermissionDTO::getId).collect(Collectors.toList()));
                rolePermissionService.updateRolePermission(updateRolePermissionDTO);
            }
        }
        return psiRoles.get(0);
    }
    private PsiRole defaultRole(PsiUser psiUser){
        PsiRole psiRole = new PsiRole();
        psiRole.setRoleName("管理员");
        psiRole.setBelongUserId(psiUser.getBelongUserId());
        psiRole.setBelongSupplierId(0L);
        psiRole.setStatus(1);
        psiRole.setCountryId(psiUser.getCountryId());
        psiRole.setCreatedUserId(0L);
        psiRole.setIsDeleted(0);
       return psiRole;
    }
}