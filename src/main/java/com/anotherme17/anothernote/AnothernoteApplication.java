package com.anotherme17.anothernote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
@EnableAutoConfiguration
@EnableScheduling
@EnableCaching// 标注启动了缓存
public class AnothernoteApplication /*extends SpringBootServletInitializer */ {

    private static ConfigurableApplicationContext sContext;

    /*Swagger*/
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        ApiInfo apiInfo = new ApiInfo("AnotherNote", "网络记事本", "1.0.0",
                "", "anotherme@aliyun.com", null, null);
        Docket docket = new Docket(DocumentationType.SWAGGER_2).select().paths(regex("/*/*.*")).build()
                .apiInfo(apiInfo).useDefaultResponseMessages(false);
        return docket;
    }

   /* @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AnothernoteApplication.class);
    }*/

    public static void main(String[] args) {
        sContext = SpringApplication.run(AnothernoteApplication.class, args);
    }

    public static <T> T getBean(String name, Class<T> aClass) {
        return sContext.getBean(name, aClass);
    }
}
