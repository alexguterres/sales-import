package com.br.tools.salesimport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SalesImportApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesImportApplication.class, args);
	}

}
