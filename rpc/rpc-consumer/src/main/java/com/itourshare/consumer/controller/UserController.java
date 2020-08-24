package com.itourshare.consumer.controller;

import org.itourshare.api.entity.User;
import org.itourshare.api.service.UserService;
import org.itourshare.rpc.annotation.ProxyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : UserController
 * @Description :
 * @Author : its
 * @Date: 2020-08-19 19:02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @ProxyService
    private UserService userService;

    @RequestMapping("/info")
    private User getUser(Integer id) {
        return userService.getUser(id);
    }

}
