package com.timor.service;

import com.timor.domain.User;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.*;

/**
 * @author CXH
 * @description
 * @create 2022-07-04 21:24
 */
@DubboService
@Service
public class UserDaoImpl implements UserDao{

    public User selectByUsername(String username) {
       //代替数据库，表示查询不到用户
        return null;
    }

    @Override
    public User selectByEmail(String email) {
        return null;
    }

    @Override
    public User selectByTelephone(String telephone) {
        return null;
    }

    @Override
    public User selectByUsernameOrEmailOrTelephone(String username, String email, String telephone) {
        //写sql通过OR来判断是否有不满足要求的情况，有就查询失败，return user;
        return null;
    }


}
