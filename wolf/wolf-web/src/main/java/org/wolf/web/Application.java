package org.wolf.web;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("org.wolf.dao")
@ComponentScan(basePackages={"org.wolf"})
@EnableTransactionManagement 
public class Application  extends SpringBootServletInitializer{
	private static final Logger logger=LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		  ApplicationContext ctx = SpringApplication.run(Application.class, args);  
	        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();  
	        for (String profile : activeProfiles) {  
	            logger.warn("WOLF 使用profile为:{}" , profile);  
	        }
	        logger.warn("---------WOLF IS READY---------");  
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}
}
