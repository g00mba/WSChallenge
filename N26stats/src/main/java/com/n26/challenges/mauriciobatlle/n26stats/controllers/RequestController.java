package com.n26.challenges.mauriciobatlle.n26stats.controllers;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.n26.challenges.mauriciobatlle.n26stats.pojos.StatResponse; 
import com.n26.challenges.mauriciobatlle.n26stats.pojos.persistence.Transaction;
@RestController
public class RequestController {
	public static volatile Map<Double, Long> statsCache=Collections.synchronizedMap(new LinkedHashMap<Double,Long>(60000,7));
	public static volatile StatResponse stats;
	@Async
	private static void UpdateStats() {}
	
	@Async
	@PostMapping("/transactions")
	public CompletableFuture <ResponseEntity<HttpStatus>> transactions(@RequestBody Transaction currentTransaction) {
		return CompletableFuture.completedFuture(new ResponseEntity<HttpStatus>(HttpStatus.CREATED));
		
	}
	@Async
	@GetMapping("/statistics")
	public StatResponse statistics() {
		return stats;} 
}
