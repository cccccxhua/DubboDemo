package com.timor.service;

import com.timor.domain.User;

import java.util.Map;

/**
 * @author CXH
 * @description
 * @create 2022-07-04 22:03
 */
public interface Register {
    Map<String,Object> register(User user);
}
