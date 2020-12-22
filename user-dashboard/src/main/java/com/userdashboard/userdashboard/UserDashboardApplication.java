package com.userdashboard.userdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
//@RibbonClient(name = "load-balancing-server",configuration = RibbonConfiguration.class)
public class UserDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDashboardApplication.class, args);
	}

}
