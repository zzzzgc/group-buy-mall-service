package com.xiguo.www.group;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ZGC
 * @date Created in 下午 12:14 2018/8/26
 */
@SpringBootApplication
@EnableScheduling
public class GroupApplication{
//public class GroupApplication extends SpringBootServletInitializer {

    private static ApplicationContext applicationContext;


//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(GroupApplication.class);
//    }

    public static void main(String[] args) {
//        ConfigurableApplicationContext ctx = SpringApplication.run(GroupApplication.class, args);
        SpringApplication springApplication = new SpringApplication(GroupApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
        System.out.println("--------------------------------------spring boot 配置加载完毕-----------------------------------------------------");
    }
}
