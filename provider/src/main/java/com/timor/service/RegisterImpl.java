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
//    //8-16个字符，至少1个大写字母，1个小写字母和1个数字,不能包含特殊字符（非数字字母）
//    public static final String password = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
//    public static final String telephone = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";
    @Autowired
    private UserDaoImpl userDao;

    public Map<String,Object> register(User user){
        //3次查询效率较低
        HashMap<String, Object> map = new HashMap<>();
//        //验证账号
//        User u = userDao.selectByUsername(user.getUsername());
//        if(u!=null){
//            //数据库中已有该账号
//            map.put("usernameMsg", "该账号已存在！");
//            return map;
//        }
//        u = userDao.selectByEmail(user.getEmail());
//        if(u!=null){
//            //数据库中已有该邮箱
//            map.put("emailMsg", "该邮箱已被注册！");
//            return map;
//        }
//        u=userDao.selectByTelephone(user.getTelephone());
//        if(u!=null){
//            //数据库中已有该手机号
//            map.put("phoneMsg", "该手机号已被注册");
//            return map;
//        }
        //一次查询,但无法给到用户准确提示
        //如果采用缓存redis或者消息队列RabbitMQ也不能较少数据库访问次数
        User u= userDao.selectByUsernameOrEmailOrTelephone(user.getUsername(), user.getEmail(), user.getTelephone());
        if (u!=null){
            map.put("usernameMsg", "请确保用户名未被使用,邮箱和手机号未被注册");
            return map;
        }
        //注册用户
        //md5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return null;
    }
}
