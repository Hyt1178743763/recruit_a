package com.hyt.web.controller;


import com.hyt.model.Tables;
import com.hyt.service.TableServiceImpl;
import com.hyt.util.FileSaveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    @RequestMapping("index2")
    public String login2(){
        return "front/index2";
    }
    @RequestMapping("index3")
    public String login3(){
        return "front/index3";
    }
    @RequestMapping("index4")
    public String login4(){
        return "front/index4";
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

    @RequestMapping("add1")
    public String caseInsert(Tables tables, MultipartFile[] file, HttpServletRequest request){
        try {

            FileSaveUtil util=new FileSaveUtil(file,tables,request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableService.addTable(tables);
        return "table/success";
    }



    @RequestMapping("find1")
    public String findTable1(Model model){

        return "front/lookDetail";
    }



    @RequestMapping("find")
    public String findTable(Model model){
        //查找报名表
        List<Tables> tables1 = tableService.findTables();
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
