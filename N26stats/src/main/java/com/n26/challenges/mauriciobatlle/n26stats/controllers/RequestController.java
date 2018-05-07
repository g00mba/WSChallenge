package com.n26.challenges.mauriciobatlle.n26stats.controllers;
import java.time.Instant;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.n26.challenges.mauriciobatlle.n26stats.pojos.StatResponse;
import com.n26.challenges.mauriciobatlle.n26stats.pojos.inputPojo;
import com.n26.challenges.mauriciobatlle.n26stats.pojos.persistence.Transaction;
import com.n26.challenges.mauriciobatlle.n26stats.repositories.TransactionRepository;
import com.n26.challenges.mauriciobatlle.n26stats.services.AsyncServices;

@RestController
public class RequestController {
	
	public static volatile Map<Long, Double> CurrencyCache=Collections.synchronizedMap(new LinkedHashMap<Long,Double>(60000,7));
	public static volatile Map<Long, Integer> counterCache=Collections.synchronizedMap(new LinkedHashMap<Long,Integer>(60000,7));
	public static volatile Long minTimestamp=0L;
	public static volatile Long maxTimestamp=0L;
	public static volatile Double min=0.0;
	public static volatile Double max=0.0;
	@Autowired
	public  TransactionRepository repo;
	public AsyncServices asyncsrv = new AsyncServices();
	public static volatile StatResponse stats=new StatResponse();

	

	
	@PostMapping("/transactions")
	@ResponseBody
	public void transactions(@RequestBody inputPojo currentTransaction,HttpServletResponse response) throws InterruptedException {

		Transaction persistentElement = new Transaction(currentTransaction.getAmount(),currentTransaction.getTime());
		repo.save(persistentElement);
		Long currentEpoch=Instant.now().toEpochMilli();
		Long timeOffset=currentEpoch-currentTransaction.getTime();
		if (timeOffset<=60000) {
			asyncsrv.addToStats(currentTransaction);
			asyncsrv.updateCaches();
			asyncsrv.refreshStats();
			response.setStatus(HttpServletResponse.SC_CREATED);
		}
		else
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		
		
		
	}

	@GetMapping("/statistics")
	public StatResponse statistics() throws InterruptedException {
		asyncsrv.updateCaches();
		asyncsrv.refreshStats();
		
		return stats;} 
}
