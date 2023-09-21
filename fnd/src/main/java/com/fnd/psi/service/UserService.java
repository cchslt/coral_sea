package com.fnd.psi.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fnd.psi.mapper.UserMapper;
import com.fnd.psi.model.PsiUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-21 16:23
 * @Desc:
 * @See:
 */
@Service
@Slf4j
public class UserService extends ServiceImpl<UserMapper, PsiUser> {

    @Autowired
    private UserMapper userMapper;

    public List<PsiUser> queryUserList() {
        LambdaQueryWrapper<PsiUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(PsiUser::getIsDeleted, 0);
        final List<PsiUser> psiUsers = userMapper.selectList(queryWrapper);

        log.info("sdfsdfdsf");
        return psiUsers;
    }
}
