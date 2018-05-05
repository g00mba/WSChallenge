package com.n26.challenges.mauriciobatlle.n26stats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class N26statsApplication {

	public static void main(String[] args) {
		SpringApplication.run(N26statsApplication.class, args);
	}
}
