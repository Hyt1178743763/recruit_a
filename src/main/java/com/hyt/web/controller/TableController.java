package com.hyt.web.controller;


import com.hyt.model.Tables;
import com.hyt.service.TableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @RestController 用于写API,给移动客户端提供数据,一般是返回json数据
 * @Controller 一般用于写后(有页面)
 */
@Controller
@RequestMapping("table")
public class TableController {

    @Autowired
    private TableServiceImpl tableService;

    @RequestMapping("index")
    public String login(){
        return "front/index";
    }


    /**
     * 填写报名表
     * @param tables
     * @return "table/success"
     */
    @RequestMapping("add")
    public String addTable(Tables tables){
        //报名表添加数据库
        tableService.addTable(tables);
        return "table/success";
    }


    @RequestMapping(value = "/saveStudentInfo", method= RequestMethod.POST)
    public ResponseData studentInfoSave(@RequestParam("file") MultipartFile file, Tables tables) {

        String filename = URLEncoder.encode(file.getOriginalFilename(), "utf-8");

        InputStream inputStream = file.getInputStream();

	//上传到第三方服务器（例如使用FTP传到自己搭建的FTP服务器）

        tables.setPicture("ftp://ftphost:port/imageDir/" + filename);

	//在Dao层将学生信息存到数据库

	//其他

        return ResponseData.success("success","success");
    }






    @RequestMapping("find")
    public String findTable(Model model){
        //查找报名表
        List<Tables> tables1 = tableService.findTables();
        System.out.println(tables1);
        model.addAttribute("tables1",tables1);
        return "table/list";
    }

    @RequestMapping("detail")
    public String detail(Integer id,Model model){
        System.out.println("id:"+id);
        //通过id查找报名表
        Tables tables2 = tableService.findById(id);
        if(tables2!=null){
            model.addAttribute("table",tables2);
        }
        return "front/lookDetail";
    }

}
