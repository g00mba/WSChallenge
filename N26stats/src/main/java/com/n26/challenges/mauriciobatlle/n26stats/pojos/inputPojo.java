package com.n26.challenges.mauriciobatlle.n26stats.pojos;



import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class inputPojo {
	
	@JsonProperty("amount")
	private Double amount;
	@JsonProperty("timestamp")
	private Long time;

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
