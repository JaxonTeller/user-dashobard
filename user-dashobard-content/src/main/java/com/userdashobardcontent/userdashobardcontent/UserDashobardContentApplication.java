package com.userdashobardcontent.userdashobardcontent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserDashobardContentApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDashobardContentApplication.class, args);
	}

}
