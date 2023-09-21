package com.fnd.psi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.dto.PageDTO;
import com.fnd.psi.dto.vo.ResultVo;
import com.fnd.psi.mapper.UserMapper;
import com.fnd.psi.model.PsiUser;
import com.fnd.psi.service.UserService;
import com.fnd.psi.utils.CopyBeanUtils;
import com.fnd.psi.utils.ResultUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-21 18:20
 * @Desc:
 * @See:
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, PsiUser> implements UserService {

    ResultUtils resultUtils;
    UserMapper userMapper;

    @Override
    public List<PsiUser> queryUserList() {
        LambdaQueryWrapper<PsiUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(PsiUser::getIsDeleted, 0);
        final List<PsiUser> psiUsers = userMapper.selectList(queryWrapper);

        log.info("sdfsdfdsf");
        return psiUsers;
    }

    @Override
    public ResultVo<PageDTO<PsiUser>> pageUserList() {

        IPage<PsiUser> page = new Page<>(1, 10);
        LambdaQueryWrapper<PsiUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(PsiUser::getIsDeleted, 0);
        IPage<PsiUser> selectPage = userMapper.selectPage(page, queryWrapper);

        PageDTO<PsiUser> resultPage= CopyBeanUtils.convert(selectPage, PageDTO.class);

        return resultUtils.returnSuccess(resultPage);
    }
}
