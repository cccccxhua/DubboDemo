package com.timor.controller;

import com.timor.domain.User;
import com.timor.service.Register;
import com.timor.service.UserDao;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author CXH
 * @description
 * @create 2022-07-04 15:07
 */
@Controller
public class UserController {

    @DubboReference
    private UserDao userDao;

    @DubboReference
    private Register register;


    @GetMapping(value = "/user/{id}")
    @ResponseBody
    public String getUser(@PathVariable("id") String id){
        System.out.println(id);
        System.out.println("得到用户信息");
        return "succeed";
    }
    @GetMapping(value = "/register")
    public String getRegister(){
        return "/register";
    }
    @PostMapping(value = "/register")
    public String register(Model model , User user){
        Map<String, Object> map = register.register(user);
        if(map.isEmpty() || map==null) {
            //成功
            return "/success";
        }else{
            //失败
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            model.addAttribute("emailMsg", map.get("emailMsg"));
            model.addAttribute("phoneMsg", map.get("phoneMsg"));
            return "/register";
        }
    }


}
