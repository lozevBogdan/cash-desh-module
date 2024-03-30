package com.cashdesh.cashdesh.module.dto;

import java.math.BigDecimal;
import java.util.Map;

import com.cashdesh.cashdesh.module.validation.ValidDenominations;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TransactionRequestDto {
	@NotNull
	@Positive
	private BigDecimal amount;

	@NotBlank
	private String currency;

    @Valid
    @ValidDenominations
	private Map<Integer, Integer> denominations;

	@NotBlank
	private String type;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<Integer, Integer> getDenominations() {
		return denominations;
	}

	public void setDenominations(Map<Integer, Integer> denominations) {
		this.denominations = denominations;
	}

}
