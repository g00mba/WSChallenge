package com.n26.challenges.mauriciobatlle.n26stats.pojos.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private Double amount;
	private Long time;
	
	protected Transaction() {}
	public Transaction(Double amount, Long time) {
		this.amount=amount;
		this.time=time;
	}
	public int getId() {
		return id;
	}
	public void setId(int tid) {
		this.id = tid;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}

	
}
