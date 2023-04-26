package com.pichincha.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@SpringBootApplication()
@EnableR2dbcAuditing
public class WsBankTransactionApplication {
	public static void main(String[] args) {
		SpringApplication.run(WsBankTransactionApplication.class, args);
	}
}
