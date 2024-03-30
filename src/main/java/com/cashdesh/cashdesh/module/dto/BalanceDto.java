package com.cashdesh.cashdesh.module.dto;

import java.math.BigDecimal;
import java.util.Map;

public class BalanceDto {

	private String currency;
	
	private BigDecimal amount;

	private Map<Integer, Integer> denominations;

	public BalanceDto(String currency, BigDecimal amount, Map<Integer, Integer> denominations) {
		super();
		this.currency = currency;
		this.amount = amount;
		this.denominations = denominations;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Map<Integer, Integer> getDenominations() {
		return denominations;
	}

	public void setDenominations(Map<Integer, Integer> denominations) {
		this.denominations = denominations;
	}

}
