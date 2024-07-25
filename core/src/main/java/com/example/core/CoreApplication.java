package com.example.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {

		SpringApplication.run(CoreApplication.class, args);
		System.out.println("core 実行中");
	}

}