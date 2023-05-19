package com.mbtiapp.mbtiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
public class MbtiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MbtiServiceApplication.class, args);
	}

}
