package com.n26.challenges.mauriciobatlle.n26stats.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.n26.challenges.mauriciobatlle.n26stats.pojos.inputPojo;
import static com.n26.challenges.mauriciobatlle.n26stats.controllers.RequestController.counterCache;
import static com.n26.challenges.mauriciobatlle.n26stats.controllers.RequestController.min;
import static com.n26.challenges.mauriciobatlle.n26stats.controllers.RequestController.max;
import static com.n26.challenges.mauriciobatlle.n26stats.controllers.RequestController.minTimestamp;
import static com.n26.challenges.mauriciobatlle.n26stats.controllers.RequestController.maxTimestamp;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.stream.LongStream;

import static com.n26.challenges.mauriciobatlle.n26stats.controllers.RequestController.CurrencyCache;
import static com.n26.challenges.mauriciobatlle.n26stats.controllers.RequestController.stats;
@Service
public class AsyncServices  {

	@Async
	public CompletableFuture<Boolean> addToStats(inputPojo currentTransaction) throws InterruptedException  {
		

			try {
				//totally forgot about the min and max! :(
				if (min==0.0) {
					min=currentTransaction.getAmount();
					minTimestamp=currentTransaction.getTime();
					}
				else if(min>currentTransaction.getAmount())
					{min=currentTransaction.getAmount();
					minTimestamp=currentTransaction.getTime();}
				
				if (max<currentTransaction.getAmount())
					max=currentTransaction.getAmount();
				
				CurrencyCache.computeIfPresent(currentTransaction.getTime(), (key,value)->value+currentTransaction.getAmount());
				CurrencyCache.putIfAbsent(currentTransaction.getTime(), currentTransaction.getAmount());

				
				
				counterCache.computeIfPresent(currentTransaction.getTime(),(key,value)->value+1);
				counterCache.putIfAbsent(currentTransaction.getTime(), 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
			return CompletableFuture.completedFuture(true);

	}
	@Async
	public CompletableFuture<Boolean> refreshStats()  throws InterruptedException {
		try {
			Long totalTransactions=counterCache.values()
					.stream().
					mapToLong(Integer::toUnsignedLong).sum();

			Double totalCurrency = CurrencyCache.values().stream().mapToDouble(Double::doubleValue).sum();
			stats.setCount(totalTransactions);		
			stats.setAvg((double) (totalCurrency/totalTransactions));
			stats.setMax(max);
			stats.setMin(min);
			stats.setSum(totalCurrency);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return CompletableFuture.completedFuture(true);

	}
	@Async
	public CompletableFuture<Boolean> updateCaches() throws InterruptedException {
		try {
			Long currentThreshold=Instant.now().toEpochMilli()-60000;
			CurrencyCache.keySet().removeIf(timestamp->timestamp<currentThreshold);
			counterCache.keySet().removeIf(timestamp->timestamp<currentThreshold);
			if (minTimestamp<currentThreshold) {
				min=0.0;
				minTimestamp=0L;
			}
			if (maxTimestamp<currentThreshold) {
				max=0.0;
				minTimestamp=0L;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return CompletableFuture.completedFuture(true);

	}
		
		



	

}
