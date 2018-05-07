package com.n26.challenges.mauriciobatlle.n26stats.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;

import com.n26.challenges.mauriciobatlle.n26stats.pojos.inputPojo;
import com.n26.challenges.mauriciobatlle.n26stats.pojos.persistence.Transaction;
import com.n26.challenges.mauriciobatlle.n26stats.repositories.TransactionRepository;
import com.n26.challenges.mauriciobatlle.n26stats.services.AsyncServices;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAsync
public class N26statsApplicationTests {
	@Autowired
	private TransactionRepository tranRepo;

	@Test
	public void contextLoads() {
	}
	@Autowired
	AsyncServices asyncTest;
	 @Test
	 public void testVoidAsync() throws InterruptedException {
		 System.out.println("Start Async method, this should be main" + Thread .currentThread().getName());

	 }
	 
	 
	 @Test
	 public void testVoidInsert() {
		 Transaction tran = new Transaction(233.54,1525623313246L);

		 tranRepo.save(tran);
	 }

}
