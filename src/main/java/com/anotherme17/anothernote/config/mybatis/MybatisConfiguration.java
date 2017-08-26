package com.anotherme17.anothernote.config.mybatis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import java.io.IOException;

/**
 * mybatis 配置文件
 */
@SpringBootApplication
@EnableTransactionManagement
public class MybatisConfiguration implements TransactionManagementConfigurer {

    private static Log logger = LogFactory.getLog(MybatisConfiguration.class);

    //  配置类型别名
    @Value("${mybatis.typeAliasesPackage}")
    private String typeAliasesPackage;

    //  配置mapper的扫描，找到所有的mapper.xml映射文件
    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

    //  加载全局的配置文件
    @Value("${mybatis.configLocation}")
    private String configLocation;


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public org.apache.tomcat.jdbc.pool.DataSource dataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }
    // DataSource配置
    //  @Bean
    //  @ConfigurationProperties(prefix = "spring.datasource")
    //  public DruidDataSource dataSource() {
    //      return new com.alibaba.druid.pool.DruidDataSource();
    //  }

    // 提供SqlSeesion
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() {
        try {
            SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dataSource());

            // 读取配置
            sessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);

            //
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources(mapperLocations);
            sessionFactoryBean.setMapperLocations(resources);
            //      //
            sessionFactoryBean.setConfigLocation(
                    new DefaultResourceLoader().getResource(configLocation));

            //添加插件  （改为使用配置文件加载了）
            //          sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper()});

            return sessionFactoryBean.getObject();
        } catch (IOException e) {
            logger.warn("mybatis resolver mapper*xml is error");
            return null;
        } catch (Exception e) {
            logger.warn("mybatis sqlSessionFactoryBean create error");
            return null;
        }
    }


    //  @Bean
    //    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    //        return new SqlSessionTemplate(sqlSessionFactory);
    //    }

    //  @Bean
    //  public PlatformTransactionManager transactionManager(){
    //      return new DataSourceTransactionManager(dataSource);
    //  }


    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


}