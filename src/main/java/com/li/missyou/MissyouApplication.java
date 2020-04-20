package com.li.missyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MissyouApplication {

	public static void main(String[] args) {
		SpringApplication.run(MissyouApplication.class, args);
	}

}
