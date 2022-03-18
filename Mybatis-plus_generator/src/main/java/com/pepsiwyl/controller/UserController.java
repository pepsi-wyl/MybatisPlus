package com.pepsiwyl.controller;


import com.pepsiwyl.service.UserService;
import com.pepsiwyl.utils.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pepsi-wyl
 * @since 2022-03-05
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier(value = "userService")
    UserService userService;

    @GetMapping("/t")
    public String t() {
        return JacksonUtils.toJson(userService.list());
    }

}

