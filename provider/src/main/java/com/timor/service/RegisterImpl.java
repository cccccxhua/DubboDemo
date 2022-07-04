package com.timor.service;


import com.timor.domain.User;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CXH
 * @description
 * @create 2022-07-04 22:04
 */
@DubboService
@Service
public class RegisterImpl implements Register{
    //8-16个字符，至少1个大写字母，1个小写字母和1个数字,不能包含特殊字符（非数字字母）
    public static final String password = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
    public static final String telephone = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";
    @Autowired
    private UserDaoImpl userDao;

    public Map<String,Object> register(User user){
        HashMap<String, Object> map = new HashMap<>();
        if(user==null){
            //判空
            throw new IllegalArgumentException("参数不能为空!");
        }
        if(StringUtils.isBlank(user.getUsername())){
            //用户名不能为空
            map.put("usernameMsg", "账号不能为空！");
            return map;
        }
        if(StringUtils.isBlank(user.getPassword())){
            //密码不能为空
            map.put("passwordMsg", "密码不能为空！");
            return map;
        }
        if(!user.getPassword().matches(password)){
            //密码必须是8-16个字符，至少1个大写字母，1个小写字母和1个数字,不能包含特殊字符
            map.put("passwordMsg", "密码必须是8-16个字符，至少1个大写字母，1个小写字母和1个数字,不能包含特殊字符！");
            return map;
        }
        if(StringUtils.isBlank(user.getEmail())){
            //邮箱不能为空
            map.put("emailMsg", "邮箱不能为空！");
            return map;
        }
        if(StringUtils.isBlank(user.getTelephone())){
            //手机号不能为空
            map.put("phoneMsg", "手机号不能为空！");
            return map;
        }
        if(!user.getTelephone().matches(telephone)){
            //请输入正确的手机号
            map.put("phoneMsg", "请输入正确的手机号!");
            return map;
        }

        //验证账号
        User u = userDao.selectByUsername(user.getUsername());
        if(u!=null){
            //数据库中已有该账号
            map.put("usernameMsg", "该账号已存在！");
            return map;
        }
        u = userDao.selectByEmail(user.getEmail());
        if(u!=null){
            //数据库中已有该邮箱
            map.put("emailMsg", "该邮箱已被注册！");
            return map;
        }
        u=userDao.selectByTelephone(user.getTelephone());
        if(u!=null){
            //数据库中已有该手机号
            map.put("phoneMsg", "该手机号已被注册");
            return map;
        }
        //注册用户
        //md5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return map;
    }
}
