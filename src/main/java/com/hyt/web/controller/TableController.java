package com.hyt.web.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.model.Tables;
import com.hyt.service.TableServiceImpl;
import com.hyt.config.FileSaveUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @RestController 用于写API,给移动客户端提供数据,一般是返回json数据
 * @Controller 一般用于写后(有页面)
 */
@Controller
@RequestMapping(value = "/table")
public class TableController {

    @Autowired
    private TableServiceImpl tableService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

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


    /**
     * @Author 胡宜涛
     * 查找返回所有的表
     * @param model
     * @return
     */
    @RequestMapping("find")
    public String findTable(Model model){
        //查找报名表
        List<Tables> tables1 = tableService.findTables();
        model.addAttribute("tables1",tables1);
        return "backoffice/order-list";
    }

    @RequestMapping(value = "/allTables")
    public String list(Model model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {

        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum, pageSize);
        //startPage后紧跟的这个查询就是分页查询
        List<Tables> tables = tableService.findTables();
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<Tables>(tables, 5);

        model.addAttribute("pageInfo", pageInfo);

        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "backoffice/order-list1";
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
