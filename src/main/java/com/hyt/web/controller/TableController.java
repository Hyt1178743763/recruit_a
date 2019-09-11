package com.hyt.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.hyt.config.Base64Utils;
import com.hyt.config.FileUtil;
import com.hyt.model.RCode;
import com.hyt.model.Tables;
import com.hyt.service.TableServiceImpl;
import com.hyt.config.FileSaveUtil;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

/**
 * @RestController 用于写API, 给移动客户端提供数据, 一般是返回json数据
 * @Controller 一般用于写后(有页面)
 */
@Controller
@RequestMapping(value = "/table")
public class TableController {

    @Autowired
    private TableServiceImpl tableService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("index")
    public String login() {
        return "front/index";
    }

    @RequestMapping("index2")
    public String login2() {
        return "front/index2";
    }

    @RequestMapping("index3")
    public String login3() {
        return "front/index3";
    }

    @RequestMapping("index4")
    public String login4() {
        return "front/index4";
    }

    @RequestMapping("index5")
    public String login5() {
        return "front/index5";
    }
    @RequestMapping("index6")
    public String login6() {
        return "build/index";
    }

    @RequestMapping("find1")
    public String findTable1(Model model) {
        return "front/lookDetail";
    }

    @RequestMapping("baoming")
    public String findTable2() {
        return "front/baoming";
    }

    //base64 转码的方法
    public boolean Base64ToImage(String imgStr,String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片
        if (StringUtil.isEmpty(imgStr)) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 填写报名表
     *
     * @param tables
     * @return code
     */
    @RequestMapping("add")
    @ResponseBody
    public RCode addTable(@RequestBody Tables tables) {
        RCode code = new RCode();
        if (tables.getPicture()==null||tables.getDormitory()==null||tables.getQqnum()==null||
        tables.getTraget()==null||tables.getFuture()==null||tables.getLikes()==null||
        tables.getIntroduction()==null||tables.getOrganization()==null||tables.getClasses()==null||
        tables.getPhonenum()==null||tables.getGender()==null||tables.getName()==null){
            code.setMsg("您有未填写的选项！！！");
            return code;
        }else{
            //查出所有表，看看有没有注册过学号判断
            Boolean bool = false;
            List<Tables> tables1 = tableService.findTables();
            for (Tables tab : tables1) {
                if (tables.getDormitory().equals(tab.getDormitory())) {
                    bool = true;
                }
            }
            if (bool) {
                code.setMsg("每人只有一次机会，请不要重复提交");
            } else {
                TableController tableController = new TableController();
                // System.out.println(tables);
                String base64Data = tables.getPicture().split(",")[1];
                String name = null;
                String Dir = "C:\\static\\";
                File file = new File(Dir);
                if (!file.exists()) {
                    file.mkdir();
                }
                name = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
                tables.setPicture(name);
                name = Dir + name;
                tableController.Base64ToImage(base64Data,name);


                tableService.addTable(tables);
                code.setMsg("提交成功");
            }
            code.setCode("1");
            return code;
        }
        /*//报名表添加数据库
        tableService.addTable(tables);
        return "success";*/
    }


    //报名表添加1
    @RequestMapping("add1")
    public String caseInsert(Tables tables, MultipartFile[] file, HttpServletRequest request) {
        System.out.println(tables);
        //查出所有表，看看有没有注册过
        Boolean bool = false;
        List<Tables> tables1 = tableService.findTables();
        for (Tables tab : tables1) {
            if (tables.getDormitory().equals(tab.getDormitory())) {
                // System.out.println("已经注册过了");
                bool = true;
            }
        }
        if (bool) {
            return "table/false";
        } else {
            try {
                FileSaveUtil util = new FileSaveUtil(file, tables, request);
            } catch (IOException e) {
                e.printStackTrace();
            }
            tableService.addTable(tables);
            return "table/success";
        }

    }
    /**
     * 查找返回所有的表
     *
     * @param model
     * @return
     */
    @RequestMapping("find")
    public String findTable(Model model) {
        //查找报名表
        List<Tables> tables1 = tableService.findTables();
        model.addAttribute("tables1", tables1);
        return "backoffice/order-list";
    }

    @RequestMapping(value = "/allTables")
    public String list(Model model, @RequestParam(defaultValue = "2") Integer pageNum, @RequestParam(defaultValue = "6") Integer pageSize) {
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum, pageSize);
        //startPage后紧跟的这个查询就是分页查询
        List<Tables> tables = tableService.findTables();
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<Tables>(tables, 5);

        model.addAttribute("pageInfo", pageInfo);
        System.out.println(pageInfo);
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
    public String detail(Integer id, Model model) {
        System.out.println("id:" + id);
        //通过id查找报名表
        Tables tables2 = tableService.findById(id);
        if (tables2 != null) {
            model.addAttribute("table", tables2);
        }
        return "front/lookDetail1";
    }

    /**
     * excel表导出
     *
     * @param request
     * @param response
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        List<Tables> tableList = tableService.findTables();
        // 创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建表
        HSSFSheet sheet = workbook.createSheet("报名信息");
        // 创建行
        HSSFRow row = sheet.createRow(0);
        // 创建单元格样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        // 表头
        String[] head = {"姓名", "性别", "手机号", "qq", "班级", "宿舍", "已投组织", "自我介绍", "爱好", "实验室学习展望", "目标", "图片"};
        HSSFCell cell;
        // 设置表头
        for (int iHead = 0; iHead < head.length; iHead++) {
            cell = row.createCell(iHead);
            cell.setCellValue(head[iHead]);
            cell.setCellStyle(cellStyle);
        }
        // 设置表格内容
        for (int iBody = 0; iBody < tableList.size(); iBody++) {
            row = sheet.createRow(iBody + 1);
            Tables tables = tableList.get(iBody);
            String[] tableArray = new String[12];
            tableArray[0] = tables.getName();
            tableArray[1] = tables.getGender();
            tableArray[2] = tables.getPhonenum();
            tableArray[3] = tables.getQqnum();
            tableArray[4] = tables.getClasses();
            tableArray[5] = tables.getDormitory();
            tableArray[6] = tables.getOrganization();
            tableArray[7] = tables.getIntroduction();
            tableArray[8] = tables.getLikes();
            tableArray[9] = tables.getFuture();
            tableArray[10] = tables.getTraget();
            tableArray[11] = tables.getPicture();
            for (int iArray = 0; iArray < tableArray.length; iArray++) {
                row.createCell(iArray).setCellValue(tableArray[iArray]);
            }
        }
        // 生成Excel文件
        FileUtil.createFile(response, workbook);
    }

}
