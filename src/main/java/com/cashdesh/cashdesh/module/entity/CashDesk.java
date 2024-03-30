package com.cashdesh.cashdesh.module.entity;

import java.math.BigDecimal;
import java.util.Map;

public class CashDesk {

	private BigDecimal amountBGN;
	// Map<Denomination, Quantity>
	private Map<Integer, Integer> denominationsBGN;
	private BigDecimal amountEUR;
	// Map<Denomination, Quantity>
	private Map<Integer, Integer> denominationsEUR;

	public CashDesk(BigDecimal amountBGN, Map<Integer, Integer> denominationsBGN, BigDecimal amountEUR,
			Map<Integer, Integer> denominationsEUR) {
		this.amountBGN = amountBGN;
		this.denominationsBGN = denominationsBGN;
		this.amountEUR = amountEUR;
		this.denominationsEUR = denominationsEUR;
	}

	public BigDecimal getAmountBGN() {
		return amountBGN;
	}

	public Map<Integer, Integer> getDenominationsBGN() {
		return denominationsBGN;
	}

	public BigDecimal getAmountEUR() {
		return amountEUR;
	}

	public Map<Integer, Integer> getDenominationsEUR() {
		return denominationsEUR;
	}

	public void setAmountBGN(BigDecimal amountBGN) {
		this.amountBGN = amountBGN;
	}

	public void setDenominationsBGN(Map<Integer, Integer> denominationsBGN) {
		this.denominationsBGN = denominationsBGN;
	}

	public void setAmountEUR(BigDecimal amountEUR) {
		this.amountEUR = amountEUR;
	}

	public void setDenominationsEUR(Map<Integer, Integer> denominationsEUR) {
		this.denominationsEUR = denominationsEUR;
	}

}
