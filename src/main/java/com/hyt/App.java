package com.hyt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.gyf.dbconfig","com.gyf.datasource","com.gyf.web","com.gyf.test1.service","com.gyf.test2.service"})
@MapperScan(basePackages = "com.hyt.mapper")
@ComponentScan(basePackages = {"com.hyt"})
public class App
{
    public static void main( String[] args )
    {
        //启动springboot项目
        SpringApplication.run(App.class,args);
    }
}
