package com.convertlab;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan(value = "com.convertlab.mapper")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class TestoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestoneApplication.class, args);
	}

}
