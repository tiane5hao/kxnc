package com.farm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.farm.mapper")
public class KxncApplication {

	public static void main(String[] args) {
		SpringApplication.run(KxncApplication.class, args);
	}
}
