package com.xiguo.www.group;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author ZGC
 * @date Created in 下午 12:14 2018/8/26
 */
@SpringBootApplication
public class GroupApplication {
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(GroupApplication.class, args);
        System.out.println("--------------------------------------spring boot 配置加载完毕-----------------------------------------------------");
    }
}
