package com.gabrielbkx.BarCodeHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.gabrielbkx.BarCodeHub")
public class BarCodeHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarCodeHubApplication.class, args);
	}

}
