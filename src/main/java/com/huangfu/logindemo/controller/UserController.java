package com.huangfu.logindemo.controller;

import com.huangfu.logindemo.domain.User;
import com.huangfu.logindemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author HuangFu
 * @create 2022/10/16 16:36
 **/
@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/login")
    public String login(User user){
        ModelAndView modelAndView = new ModelAndView();
        User loginUser = this.userService.getUser(user);
        if(loginUser!=null){
            modelAndView.addObject("user",loginUser);
            return "user";
        }else{
            return "login";
        }

    }
}
