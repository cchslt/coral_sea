package com.fnd.psi.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.dto.user.PsiUserInfoDTO;
import com.fnd.psi.dto.vo.PermissionDTO;
import com.fnd.psi.dto.vo.PermissionVO;
import com.fnd.psi.mapper.PermissionMapper;
import com.fnd.psi.model.PsiPermission;
import com.fnd.psi.service.PermissionService;
import com.fnd.psi.service.PsiUserIdentityService;
import com.fnd.psi.utils.ResultVoUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Date: 2022/1/26/026 17:32
 */
@Slf4j
@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PsiPermission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PsiUserIdentityService psiUserIdentityService;

    @Override
    public ResultVo updatePermission(PermissionVO permissionDto, HttpServletRequest request) {
        final Date date = new Date();
        PsiPermission permission = new PsiPermission();
        BeanUtils.copyProperties(permissionDto, permission);
        permission.setGmtModified(date);
        this.saveOrUpdate(permission);
        return ResultVoUtil.success();
    }

    /**
     * 资源列表
     *
     * @return
     */

    @Override
    public ResultVo permissionList() {
        final List<PermissionDTO> rolePermission = permissionMapper.getRolePermission();
        return ResultVoUtil.success(rolePermission);
    }

    public List<PermissionDTO> getRolePermissionByRolesContainButton(List<Long> roleIds) {
        List<PermissionDTO> rolePermissionByRoles = permissionMapper.getRolePermissionByRoles(roleIds);
        if (!CollectionUtils.isEmpty(rolePermissionByRoles)){
            Set<PermissionDTO> permissionDTOSet = new HashSet(rolePermissionByRoles);
            rolePermissionByRoles = new ArrayList<>(permissionDTOSet);
        }
        return builderPermissionTree(rolePermissionByRoles);
    }

    public List<PermissionDTO> builderPermissionTree(List<PermissionDTO> rolePermissionByRoles){
        if (CollectionUtils.isEmpty(rolePermissionByRoles)){
            return rolePermissionByRoles;
        }


        final Map<Long, List<PermissionDTO>> permissionDTO = rolePermissionByRoles.stream().collect(Collectors.groupingBy(PermissionDTO::getParentId));
        permissionDTO.forEach((k, v) -> v.forEach(x ->{
            final Long id = x.getId();
            if (x.getParentId()==0L){
                List<PermissionDTO> secondMenuList = getPermissionList(id, permissionDTO);
                x.setRoutes(secondMenuList);

                //设置三级菜单
                if (CollUtil.isNotEmpty(secondMenuList)) {
                    secondMenuList.forEach(secondMenu -> {
                        List<PermissionDTO> threeMenuList = getPermissionList(secondMenu.getId(), permissionDTO);
                        secondMenu.setRoutes(threeMenuList);

                        //设置4级菜单
                        if (CollUtil.isNotEmpty(threeMenuList)) {
                            threeMenuList.forEach(threeMenu -> {
                                List<PermissionDTO> fourMenuList = getPermissionList(threeMenu.getId(), permissionDTO);
                                threeMenu.setRoutes(fourMenuList);
                            });
                        }

                    });
                }
            }
        }));
        return permissionDTO.get(0L).stream()
                .sorted(Comparator.comparing(PermissionDTO::getPriority))
                .collect(Collectors.toList());
    }


    private List<PermissionDTO> getPermissionList(Long currentId, Map<Long, List<PermissionDTO>> permissionDTOMap) {
        List<PermissionDTO> permissionList = CollUtil.newArrayList();

        if(CollUtil.isNotEmpty(permissionDTOMap.get(currentId))) {
            permissionList = permissionDTOMap.get(currentId).stream()
                    .sorted(Comparator.comparing(PermissionDTO::getPriority))
                    .collect(Collectors.toList());
        }
        return permissionList;
    }

    @Override
    public HashMap<String, List<PermissionDTO>> getRolePermissionByRoles(List<Long> roleIds, PsiUserInfoDTO psiUserInfoDTO) {
        final HashMap<String, List<PermissionDTO>> roleMap = Maps.newHashMap();
        List<PermissionDTO> rolePermissionByRoles = permissionMapper.getRolePermissionByRoles(roleIds);
        if (!CollectionUtils.isEmpty(rolePermissionByRoles)){
            Set<PermissionDTO> permissionDTOSet = new HashSet(rolePermissionByRoles);
            rolePermissionByRoles = new ArrayList<PermissionDTO>(permissionDTOSet);
        }

        roleMap.put("routes",builderPermissionTree(rolePermissionByRoles));
        roleMap.put("button",rolePermissionByRoles.stream().filter(x ->x.getType().equals(1)).collect(Collectors.toList()));
        return roleMap;
    }


    //递归查询子菜单
    public List<PermissionDTO> getPermissionTreeList(List<PsiPermission> permissions, List<String> permissionIdList) {
        List<PermissionDTO> resultPermissionDto = new ArrayList<>();
        for (PsiPermission permission : permissions) {
            PermissionDTO permissionDTO = new PermissionDTO();
            BeanUtils.copyProperties(permission,permissionDTO);
            permissionDTO.setRoutes(getListByParentId(permission.getId()));
            resultPermissionDto.add(permissionDTO);
        }
        return resultPermissionDto;
    }


    public List<PermissionDTO> getListByParentId(Long parentId) {
        //获取父菜单
        List<PermissionDTO> permissionDTOS = new ArrayList<>();
        final List<PsiPermission> permissions = list(new LambdaQueryWrapper<PsiPermission>().eq(PsiPermission::getIsDeleted, false)
                .eq(PsiPermission::getStatus, false).eq(PsiPermission::getParentId, parentId).orderByDesc(PsiPermission::getId));
        permissions.forEach(x ->{
            PermissionDTO permissionDTO = new PermissionDTO();
            BeanUtils.copyProperties(x,permissionDTO);
            permissionDTO.setRoutes(getListByParentId(x.getId()));
            permissionDTOS.add(permissionDTO);
        });
        return permissionDTOS;
    }


}