package com.fnd.psi.controller;

import com.fnd.psi.dto.user.UserLoginDTO;
import com.fnd.psi.dto.vo.ResultVo;
import com.fnd.psi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-22 14:37
 * @Desc:
 * @See:
 */
@Api(value = "用户信息", tags = "用户信息")
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultVo userLogin(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.userLogin(userLoginDTO);
    }

}
