package com.timor.controller;

import com.timor.domain.User;
import com.timor.service.Register;
import com.timor.service.UserDao;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
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
    public String register(@Valid User user,BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            for(ObjectError error:errors){
                String message = error.getDefaultMessage();
                switch (message){
                    case "usernameMsg":
                        model.addAttribute("usernameMsg", "账号不能为空！");
                        return "/register";
                    case "passwordMsg":
                        model.addAttribute("passwordMsg", "密码必须是8-16个字符，至少1个大写字母，1个小写字母和1个数字,不能包含特殊字符！");
                        return "/register";
                    case "phoneMsg":
                        model.addAttribute("phoneMsg", "请输入正确的手机号");
                        return "/register";
                    case "emailMsg":
                        model.addAttribute("emailMsg", "请输入正确的邮箱");
                        return "/register";
                }
            }
        }
        Map<String, Object> map = this.register.register(user);
        if(map!=null){
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            return "/register";
        }
        return "/success";
    }


}
