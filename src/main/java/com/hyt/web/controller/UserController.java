package com.hyt.web.controller;

import com.hyt.model.User;
import com.hyt.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller //声明Rest风格的控制器
@RequestMapping("back")
public class UserController {


    @Autowired
    private UserServiceImpl userService;


   /* @RequestMapping("register")
    @ResponseBody
    public String register(String username,String password){
        //把数据保存到数据库
        userService.register(username,password);
        return "success";
    }*/

    @RequestMapping("login")
    public String toLogin(){
        return "backoffice/login";
    }

    @RequestMapping("login1")
    @ResponseBody
    public boolean login(String username, String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = userService.findUserName(username);
        if(user!=null&&username.equals(user.getUsername())&&password.equals(user.getPassword())) {
            session.setAttribute("user",user);
            return true;
        } else {
            return false;
        }
    }
    /*@RequestMapping("login1")
    public String login(User user, Model model){
        //model.addattribte(path,"test2")
        System.out.println(user);
        boolean bool = userService.findUserName(user);
        if (bool){
            model.addAttribute("user",user);
            return "backoffice/index";
        }else {
            return "backoffice/login";
        }
    }*/
    @RequestMapping("index")
    public String toIndex(){
        return "backoffice/index";
    }

}
