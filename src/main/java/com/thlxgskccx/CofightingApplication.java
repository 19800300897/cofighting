package com.thlxgskccx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.thlxgskccx.dao"})
public class CofightingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CofightingApplication.class, args);
	}

}
