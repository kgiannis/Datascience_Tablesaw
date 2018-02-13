package com.rt.Tablesaw.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.rt.*")
@EntityScan("com.rt.Tablesaw.model")
public class TablesawApplication {

	public static void main(String[] args) {
		SpringApplication.run(TablesawApplication.class, args);
	}
}
