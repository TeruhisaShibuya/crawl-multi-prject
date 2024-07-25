package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.app", "com.example.core"}) // ここで domain プロジェクトのパッケージを指定
public class AppApplication {

	public static void main(String[] args) {

		SpringApplication.run(AppApplication.class, args);
		System.out.println("app 実行中");
	}
}
