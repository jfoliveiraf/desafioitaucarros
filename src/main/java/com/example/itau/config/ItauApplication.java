package com.example.itau.config;

import com.example.itau.controller.SenhaController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication(scanBasePackages={"com.example.itau"})
@ComponentScan(basePackageClasses= SenhaController.class)
@EnableAsync
public class ItauApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItauApplication.class, args);
	}

}
