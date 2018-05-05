package com.n26.challenges.mauriciobatlle.n26stats.pojos.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long tid;
	private Double amount;
	private Long time;
	
	protected Transaction() {}
	public Transaction(Double amount, Long time) {
		this.amount=amount;
		this.time=time;
	}

}
