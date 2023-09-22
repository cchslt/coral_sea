package com.fnd.psi.controller;

import com.fnd.psi.dto.PageDTO;
import com.fnd.psi.dto.ResultVo;
import com.fnd.psi.model.PsiUser;
import com.fnd.psi.security.FndPreAuthorize;
import com.fnd.psi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-21 15:17
 * @Desc:
 * @See:
 */
@Api(value = "dd", tags = "test-信息")
@RestController
@AllArgsConstructor
public class TestController {

    private UserService userService;

    @GetMapping("test")
    @ApiOperation(value = "测试", httpMethod = "GET")
    public List<PsiUser> test() {
        return userService.queryUserList();
    }

    @GetMapping("testPage")
    @FndPreAuthorize
    @ApiOperation(value = "测试", httpMethod = "GET")
    public ResultVo<PageDTO<PsiUser>> testPage() {
        return userService.pageUserList();
    }
}
