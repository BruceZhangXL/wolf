package org.wolf.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("org.wolf")
@MapperScan("org.wolf.dao")
@EnableTransactionManagement 
public class TestApplication {
	 public static void main(String[] args) {  
	        SpringApplication.run(TestApplication.class, args);  
	 }  
}
