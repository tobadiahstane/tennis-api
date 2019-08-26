package com.tobadiahstane.tennis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TennisApiApplication {

	public static void main(String[] args) {
		SpringApplication tennisAPI = new SpringApplication(TennisApiApplication.class);
		tennisAPI.run(args);
	}

	
	
}
