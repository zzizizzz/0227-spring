package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringShopApplication.class, args);
		// run 할때 스프링 컨테이너가 만들어진다.
	}

}
