package com.xiguo.www.group.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: ZGC
 * @date Created in 2018/8/29 下午 4:08
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 扫描包的路径
                .apis(RequestHandlerSelectors.basePackage("com.xiguo.www.group"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 项目名称
                .title("微端指猴团购Api服务项目")
                // 项目描述
                .description("打造最快速度建立的后台服务接口")
                // 服务条款网址
                .termsOfServiceUrl("http://www.52xiguo.com")
                // 版本号
                .version("1.0")
                // 个人信息  创建人,个人网站和 个人邮箱
                .contact(new Contact("ZGC", "http://www.52xiguo.com", "1098766713@qq.com"))
                // 部署
                .build();
    }
}
