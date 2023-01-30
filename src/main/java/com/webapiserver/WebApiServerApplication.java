package com.webapiserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.webapiserver")
public class WebApiServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApiServerApplication.class, args);
	}

}
