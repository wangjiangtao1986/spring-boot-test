package com.icbc;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
@MapperScan("com.icbc.oa.mapper")
public class ICBCController extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ICBCController.class);
    }
	public static void main(String[] args) throws Exception {
		// devtools：是spring boot的一个热部署工具
		//设置 spring.devtools.restart.enabled 属性为false，可以关闭该特性.
		System.setProperty("spring.devtools.restart.enabled","true");  
		SpringApplication.run(ICBCController.class, args);
	}
}