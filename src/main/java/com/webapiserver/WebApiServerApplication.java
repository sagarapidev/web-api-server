package com.webapiserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.webapiserver")
public class WebApiServerApplication {

	public static void main(String[] args) {
		System.setProperty("org.apache.poi.util.POILogger", "org.apache.poi.util.NullLogger");
		System.setProperty("poi.record.max.recordlength", "1073741824");


		SpringApplication.run(WebApiServerApplication.class, args);
	}

}
