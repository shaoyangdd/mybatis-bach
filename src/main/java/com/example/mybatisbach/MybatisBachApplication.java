package com.example.mybatisbach;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.mybatisbach"})
@MapperScan("com.example.mybatisbach.dao")
@EnableAutoConfiguration
public class MybatisBachApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisBachApplication.class, args);
	}
}
