package com.n26.challenges.mauriciobatlle.n26stats;

import java.util.concurrent.Executor;


import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


import com.n26.challenges.mauriciobatlle.n26stats.repositories.TransactionRepository;
@EnableAsync
@SpringBootApplication
public class N26statsApplication {

	public static void main(String[] args) {
		SpringApplication.run(N26statsApplication.class, args);
	}
    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(16);
        executor.setMaxPoolSize(16);
        executor.setQueueCapacity(60000);
        executor.setThreadNamePrefix("N26Stats-");
        executor.initialize();
        return executor;
    }


}
