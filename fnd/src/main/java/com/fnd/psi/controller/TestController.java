package com.fnd.psi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-21 15:17
 * @Desc:
 * @See:
 */
//@Api(value = "dd", tags = "test-信息")
@RestController
public class TestController {

    @GetMapping("test")
//    @ApiOperation(value = "测试", httpMethod = "GET")
    public Integer test() {
        return 100;
    }
}
