package com.xiguo.www.group.config;

import com.xiguo.www.group.entity.GroupBuy;
import com.xiguo.www.group.entity.Noutoasiakas;
import com.xiguo.www.group.entity.Order;
import com.xiguo.www.group.entity.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Entity;
import java.util.Set;

/**
 * RestTemplate 请求的配置与注册bean
 * @author: ZGC
 * @date Created in 2018/8/27 下午 4:52
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        // 加载超时(ms)
        factory.setReadTimeout(5000);
        // 连接超时(ms)
        factory.setConnectTimeout(15000);
        return factory;
    }

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return new RepositoryRestConfigurerAdapter() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

                // 暴露接口的所有id
                final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
                provider.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
                final Set<BeanDefinition> beans = provider.findCandidateComponents("com.xiguo.www.group.entity");
                for (final BeanDefinition bean : beans) {
                    try {
                        config.exposeIdsFor(Class.forName(bean.getBeanClassName()));
                    } catch (final ClassNotFoundException e) {
                        // Can't throw ClassNotFoundException due to the method signature. Need to cast it
                        throw new IllegalStateException("Failed to expose `id` field due to", e);
                    }
                }

                // 设置restful接口允许暴露id的数据库实体表
//                config.exposeIdsFor(exposeIdsForBean);
//                config.exposeIdsFor(User.class, GroupBuy.class, Order.class,Noutoasiakas.class);
            }
        };
    }
}
