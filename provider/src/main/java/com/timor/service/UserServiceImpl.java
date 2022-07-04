package com.timor.service;

import com.timor.domain.User;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@DubboService
@Service
public class UserServiceImpl implements UserService{

    @Override
    public User getUser(String uid) {
        User user = new User();
        user.setUsername("Tom");
        return user;
    }
}