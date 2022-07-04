package com.timor.service;

import com.timor.domain.User;

/**
 * @author CXH
 * @description
 * @create 2022-07-04 21:13
 */
public interface UserDao {
    User selectByUsername(String username);//通过username查询用户
    User selectByEmail(String email);//通过email查询用户
    User selectByTelephone(String telephone);//通过手机号查询用户
}
