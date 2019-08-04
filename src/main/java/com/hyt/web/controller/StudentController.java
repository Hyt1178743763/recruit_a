package com.hyt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @RestController 用于写API,给移动客户端提供数据,一般是返回json数据
 * @Controller 一般用于写后(有页面)
 */
@Controller
@RequestMapping("stu")
public class StudentController {


  /*  @RequestMapping("list")
    public String list(Model model){
        model.addAttribute("username","gyf");
        model.addAttribute("age",20);
        List<Student> stuList = new ArrayList<Student>();
        stuList.add(new Student(1001,"zhangsan","男"));
        stuList.add(new Student(1002,"lisi","男"));
        stuList.add(new Student(1003,"wangwu","男"));
        model.addAttribute("stuList",stuList);

        return "stu/list";//找模版页面
    }
*/
}
