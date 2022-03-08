package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
@MapperScan("com.mapper")
@ServletComponentScan
public class MainApplication {
    public static void main(String[] args) {
        //使用SpringApplication类的静态方法，启动SpringBoot程序
        //方法的参数；程序的入口类+main函数的参数
        SpringApplication.run(MainApplication.class,args);
    }
}
