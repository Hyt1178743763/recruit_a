package com.hyt.web.controller;

import com.hyt.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //声明Rest风格的控制器
@RequestMapping("user")
public class UserController {
    /*@Autowired*/
  /*  private UserServiceImpl userService;


    @RequestMapping("register")
    @ResponseBody
    public String register(String username,String password){
        //把数据保存到数据库
        userService.register(username,password);
        return "success";
    }

    @RequestMapping("login")
    public String login(String username,String password){
        //model.addattribte(path,"test2")
        return "user/list";
    }*/


}
