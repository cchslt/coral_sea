package com.fnd.psi.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.vo.PermissionDTO;
import com.fnd.psi.dto.vo.UpdateRolePermissionDTO;
import com.fnd.psi.mapper.PermissionMapper;
import com.fnd.psi.mapper.RolePermissionMapper;
import com.fnd.psi.model.PsiRolePermission;
import com.fnd.psi.service.RolePermissionService;
import com.fnd.psi.utils.ResultVoUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@Slf4j
@Service("rolePermissionService")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, PsiRolePermission> implements RolePermissionService {


    @Autowired
    private PermissionMapper permissionMapper;


    public ResultVo findPermissionByRoleId(Long roleId) {
        List<PermissionDTO> permissionDTOS = Lists.newArrayList();
        final List<PsiRolePermission> permissions = list(new LambdaQueryWrapper<PsiRolePermission>().eq(PsiRolePermission::getIsDeleted, false)
                .eq(PsiRolePermission::getRoleId, roleId).orderByDesc(PsiRolePermission::getGmtCreate));
        return ResultVoUtil.success(permissionDTOS);
        }
    @Transactional
    public ResultVo updateRolePermission(UpdateRolePermissionDTO updateRolePermissionDTO) {
        final List<Long> permissionIds = updateRolePermissionDTO.getPermissionIds();
        final Long roleId = updateRolePermissionDTO.getRoleId();
        final Date date = new Date();
        this.lambdaUpdate().set(PsiRolePermission::getIsDeleted, 1)
                .eq(PsiRolePermission::getRoleId,roleId)
                .update();
        final List<PermissionDTO> rolePermission = permissionMapper.getRolePermission();

        final Set<PermissionDTO> psiPermissions = rolePermission.stream().filter(x -> permissionIds.contains(x.getId())).collect(Collectors.toSet());
        if (CollUtil.isNotEmpty(psiPermissions)){
            final Set<Long> parentIds = getParentIds(psiPermissions, rolePermission);
            log.info("【updateRolePermission】： {}", JSONUtil.toJsonStr(parentIds));
            permissionIds.addAll(parentIds);
        }
        final Set<PermissionDTO> permissionDTOSet = rolePermission.stream().filter(x -> !x.getShow() && !x.getVirtual()).collect(Collectors.toSet());
        List<PsiRolePermission> psiRolePermissions = Lists.newArrayList();
        Set<Long> permissionIdSets = Sets.newHashSet(permissionIds);
        permissionIdSets.forEach(x ->{
            PsiRolePermission psiRolePermission = new PsiRolePermission();
            psiRolePermission.setPermissionId(x);
            psiRolePermission.setGmtModified(date);
            psiRolePermission.setGmtCreate(date);
            psiRolePermission.setIsDeleted(false);
            psiRolePermission.setRoleId(roleId);
            psiRolePermissions.add(psiRolePermission);
        });
        /**
         * 补充默认的
         */
        permissionDTOSet.forEach(x->{
            PsiRolePermission psiRolePermission = new PsiRolePermission();
            psiRolePermission.setPermissionId(x.getId());
            psiRolePermission.setGmtModified(date);
            psiRolePermission.setGmtCreate(date);
            psiRolePermission.setIsDeleted(false);
            psiRolePermission.setRoleId(roleId);
            psiRolePermissions.add(psiRolePermission);
        });
        saveBatch(psiRolePermissions);

        return ResultVoUtil.success();
    }


    private static Set<Long> getParentIds(Set<PermissionDTO> psiPermissions, List<PermissionDTO> rolePermission) {
        Set<Long> parentIds = CollUtil.newHashSet();
        final Map<Long, PermissionDTO> permissionDTOMap = rolePermission.stream().collect(Collectors.toMap(PermissionDTO::getId, Function.identity()));
        psiPermissions.stream().filter(x -> x.getParentId() > 0).forEach(t -> {
            final PermissionDTO permissionDTO = permissionDTOMap.get(t.getId());
            parentIds.addAll(getIds(permissionDTO, permissionDTOMap));
        });

        return parentIds;
    }


    private static Set<Long> getIds(PermissionDTO permissionDTO, Map<Long, PermissionDTO> permissionDTOMap) {
        Set<Long> parentIds = CollUtil.newHashSet();
        PermissionDTO dto = permissionDTOMap.get(permissionDTO.getId());
        if (dto.getParentId() > 0) {
            parentIds.addAll(getIds(permissionDTOMap.get(dto.getParentId()), permissionDTOMap));
        }
        parentIds.add(dto.getId());

        return parentIds;

    }
}