package com.usersearchhistory.usersearchhistory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserSearchHistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserSearchHistoryApplication.class, args);
	}

}
