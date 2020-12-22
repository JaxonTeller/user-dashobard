package com.serviceregisterydiscoveryserver.serviceregisterydiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegisteryDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegisteryDiscoveryServerApplication.class, args);
	}

}
