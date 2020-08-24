package com.its.provider.service;

import lombok.extern.slf4j.Slf4j;
import org.itourshare.api.entity.User;
import org.itourshare.api.service.UserService;
import org.itourshare.rpc.annotation.RpcService;

/**
 * @ClassName : UserServiceImpl
 * @Description :
 * @Author : its
 * @Date: 2020-08-19 16:17
 */
@RpcService
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(Integer id) {
        log.info("rpc provider param [{}]", id);
        User user = new User();
        user.setId(123);
        user.setName("中文");
        return user;
    }
}
