package com.xiguo.www.group.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author: ZGC
 * @date Created in 2018/9/2 上午 11:46
 */
//@Configuration
public class JpaConfig {

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        // 使用默认配置即可其他的不管
//        // 数据源导入
////        em.setDataSource(dataSource());
//        // 包扫描
////        em.setPackagesToScan("package names","");
//        // 自定义配置
////         em.setJpaProperties(additionalProperties());
//
//        // 适配器
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//
//        return em;
//    }
//
////    @Bean
////    public DataSource dataSource() {
////        DriverManagerDataSource dataSource = new DriverManagerDataSource();
////        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
////        dataSource.setUrl("jdbc:mysql://localhost:3306/数据库名");
////        dataSource.setUsername("用户名");
////        dataSource.setPassword("密码");
////        return dataSource;
////    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(emf);
//        return transactionManager;
//    }
//
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
//
////    Properties additionalProperties() {
////        Properties properties = new Properties();
////        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
////        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
////        return properties;
////    }

}
