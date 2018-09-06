package com.xiguo.www.group.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.*;
import java.util.Date;

/**
 * @author: ZGC
 * @date Created in 2018/9/2 上午 11:46
 */
@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "entityManageFactoryPrimary",
//        transactionManagerRef = "transactionManagerPrimary",
//        basePackages = {"com.xiguo.www.group.entity"}
//)
public class JpaConfig {

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat otherDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//        DateFormat otherDateFormat = objectMapper.getDateFormat();

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = jsonConverter.getObjectMapper();
        objectMapper.registerModule(new Hibernate5Module());
        objectMapper.setDateFormat(new MyDateFormat(defaultDateFormat,otherDateFormat));
//        objectMapper.registerModule(new Hibernate4Module());;
        return jsonConverter;
    }


//    @Autowired
//    private DataSource  datasource;
//
//    @Primary
//    @Bean(name = "entityManagerPrimary")
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder){
//        return entityManageFactory(builder).getObject().createEntityManager();
//    }
//
//    @Primary
//    @Bean(name = "entityManageFactoryPrimary")
//    public LocalContainerEntityManagerFactoryBean entityManageFactory(EntityManagerFactoryBuilder builder){
//        DataSource dataSource = new DataSource(datasource);
//
//        LocalContainerEntityManagerFactoryBean entityManagerFactory =  builder.dataSource(dataSource)
//                .packages("com.hope.monsterlan.entity").build();
//        Properties jpaProperties = new Properties();
//        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        jpaProperties.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
//        jpaProperties.put("hibernate.connection.charSet", "utf-8");
//        jpaProperties.put("hibernate.show_sql", "false");
//        entityManagerFactory.setJpaProperties(jpaProperties);
//        return entityManagerFactory;
//    }
//
//    @Primary
//    @Bean(name = "transactionManagerPrimary")
//    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(entityManageFactory(builder).getObject());
//    }
}

class MyDateFormat extends DateFormat {

    private DateFormat defaultDateFormat;

    private DateFormat myDateFormat;
//    private SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    MyDateFormat(DateFormat myDateFormat, DateFormat defaultDateFormat) {
        this.myDateFormat = myDateFormat;
        this.defaultDateFormat = defaultDateFormat;
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return defaultDateFormat.format(date, toAppendTo, fieldPosition);
    }

    @Override
    public Date parse(String source, ParsePosition pos) {

        Date date = null;

        try {

            date = myDateFormat.parse(source, pos);
        } catch (Exception e) {

            date = defaultDateFormat.parse(source, pos);
        }

        return date;
    }

    /**
     * 主要还是装饰这个方法
     * @param source
     * @return
     * @throws ParseException
     */
    @Override
    public Date parse(String source) throws ParseException {

        Date date = null;

        try {
            // 先按我的规则来
            date = myDateFormat.parse(source);
        } catch (Exception e) {
            // 不行，那就按原先的规则吧
            date = defaultDateFormat.parse(source);
        }

        return date;
    }

    /**
     * 这里装饰clone方法的原因是因为clone方法在jackson中也有用到
     * @return
     */
    @Override
    public Object clone() {
        Object defaultDateFormat = this.defaultDateFormat.clone();
        Object MyDateFormat = this.myDateFormat;
        return new MyDateFormat((DateFormat) MyDateFormat, (DateFormat) defaultDateFormat);
    }
}
