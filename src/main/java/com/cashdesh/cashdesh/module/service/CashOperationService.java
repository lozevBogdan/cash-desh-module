package com.cashdesh.cashdesh.module.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cashdesh.cashdesh.module.dto.BalanceDto;
import com.cashdesh.cashdesh.module.entity.CashDesk;

@Service
public class CashOperationService {

	@Autowired
	private CashDesk cashDesk;
	@Autowired
	private FileLoggerService fileLoggerService;

	@Value("${fib.api.cashier.name}")
	private String user;

	public boolean withdrawal(BigDecimal amount, Map<Integer, Integer> denominations, String currency) {
		if (validate(amount, denominations)) {
			boolean //
			res = doWithdrawal(amount, denominations, currency);
			fileLoggerService.logTransaction(
					user + ": " + "WITHDRAWAL " + (res ? "SUCCESSFULLY " : "FAILED ") + amount + " " + currency);

			return res;
		}

		fileLoggerService.logTransaction(user + ": " + "WITHDRAWAL " + "VALIDATION FAILED " + amount + " " + currency);

		return false;
	}

	private boolean doWithdrawal(BigDecimal amount, Map<Integer, Integer> denominations, String currency) {
		if (currency.equals("BGN")) {

			BigDecimal //
			amountBGN = cashDesk.getAmountBGN();
			Map<Integer, Integer> //
			denominationsBGN = cashDesk.getDenominationsBGN();

			BigDecimal //
			subtractedAmount = amountBGN.subtract(amount);
			if (subtractedAmount.compareTo(BigDecimal.ZERO) < 0) {
				return false;
			}

			for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
				Integer key = entry.getKey();
				Integer val = entry.getValue();

				Integer //
				counts = denominationsBGN.get(key);
				if (counts == null) {
					return false;
				}
				Integer //
				subtractedDenomCounts = counts - val;
				if (subtractedDenomCounts < 0) {
					return false;
				}
				denominationsBGN.put(key, subtractedDenomCounts);
				cashDesk.setDenominationsBGN(denominationsBGN);
			}
			cashDesk.setAmountBGN(subtractedAmount);

			fileLoggerService.logCashBalances(amountBGN.toString(), subtractedAmount.toString(), currency);
			return true;

		} else if (currency.equals("EUR")) {
			BigDecimal //
			amountEUR = cashDesk.getAmountEUR();
			Map<Integer, Integer> //
			denominationsEUR = cashDesk.getDenominationsEUR();

			BigDecimal //
			subtractedAmount = amountEUR.subtract(amount);
			if (subtractedAmount.compareTo(BigDecimal.ZERO) < 0) {
				return false;
			}

			for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
				Integer key = entry.getKey();
				Integer val = entry.getValue();

				Integer //
				counts = denominationsEUR.get(key);
				if (counts == null) {
					return false;
				}

				Integer //
				subtractedDenomCounts = counts - val;
				if (subtractedDenomCounts < 0) {
					return false;
				}

				denominationsEUR.put(key, subtractedDenomCounts);
				cashDesk.setDenominationsEUR(denominationsEUR);

			}
			cashDesk.setAmountEUR(subtractedAmount);

			fileLoggerService.logCashBalances(amountEUR.toString(), subtractedAmount.toString(), currency);
			return true;
		}

		return false;

	}

	public void deposit(BigDecimal amount, Map<Integer, Integer> denominations, String currency) {
		if (validate(amount, denominations)) {
			doDeposit(amount, denominations, currency);
			fileLoggerService.logTransaction(
					user + ": " + "DEPOSIT "  + amount + " " + currency);
		}
	}

	private void doDeposit(BigDecimal amount, Map<Integer, Integer> denominations, String currency) {
		if (currency.endsWith("BGN")) {
			BigDecimal //
			amountBGN = cashDesk.getAmountBGN();

			Map<Integer, Integer> //
			denominationsBGN = cashDesk.getDenominationsBGN();

			for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
				Integer key = entry.getKey();
				Integer val = entry.getValue();

				if (denominationsBGN.get(key) == null) {
					denominationsBGN.put(key, val);
				} else {
					Integer oldCount = denominationsBGN.get(key);
					Integer newCounts = oldCount + val;

					denominationsBGN.remove(key);
					denominationsBGN.put(key, newCounts);
				}
			}
			BigDecimal newAmount = amountBGN.add(amount);
			cashDesk.setAmountBGN(newAmount);
			fileLoggerService.logCashBalances(amountBGN.toString(), newAmount.toString(), currency);

		} else if (currency.endsWith("EUR")) {
			BigDecimal //
			amountEUR = cashDesk.getAmountEUR();

			Map<Integer, Integer> //
			denominationsEUR = cashDesk.getDenominationsEUR();

			for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
				Integer key = entry.getKey();
				Integer val = entry.getValue();

				if (denominationsEUR.get(key) == null) {
					denominationsEUR.put(key, val);
				} else {
					Integer oldCount = denominationsEUR.get(key);
					Integer newCounts = oldCount + val;

					denominationsEUR.remove(key);
					denominationsEUR.put(key, newCounts);
				}
			}
			BigDecimal newAmount = amountEUR.add(amount);
			cashDesk.setAmountEUR(newAmount);
			fileLoggerService.logCashBalances(amountEUR.toString(), newAmount.toString(), currency);
		}
		


	}

	private boolean validate(BigDecimal amount, Map<Integer, Integer> denominations) {
		if (amount != null && !denominations.isEmpty()) {

			BigDecimal //
			denomAmount = getDenominationsAmount(denominations);
			if (denomAmount.equals(amount)) {
				return true;
			}

			return false;
		}
		return false;
	}

	private BigDecimal getDenominationsAmount(Map<Integer, Integer> denominations) {
		if (denominations != null && !denominations.isEmpty()) {
			Integer sum = 0;
			for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
				Integer key = entry.getKey();
				Integer val = entry.getValue();

				sum += key * val;

			}

			return new BigDecimal(sum);
		}
		return null;
	}

	public BalanceDto[] getBalance() {

		BalanceDto[] //
		res = new BalanceDto[2];

		res[0] = new BalanceDto("BGN", cashDesk.getAmountBGN(), cashDesk.getDenominationsBGN());
		res[1] = new BalanceDto("EUR", cashDesk.getAmountEUR(), cashDesk.getDenominationsEUR());

		return res;

	}

}
