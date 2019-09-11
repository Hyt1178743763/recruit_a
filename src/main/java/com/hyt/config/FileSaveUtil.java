package com.hyt.config;

import com.hyt.model.Tables;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileSaveUtil {
    public FileSaveUtil(MultipartFile[] files, Tables tables, HttpServletRequest request) throws IllegalStateException, IOException {
        String name=null;
        String Dir="C:\\static\\";
        File file=new File(Dir);
        if (!file.exists()){
            file.mkdir();
        }
        if (!files[0].isEmpty()){
            name= UUID.randomUUID().toString().replaceAll("-","")+"."+ FilenameUtils.getExtension(files[0].getOriginalFilename());
            tables.setPicture(name);
            files[0].transferTo(new File(Dir+ name));
        }
    }

}
