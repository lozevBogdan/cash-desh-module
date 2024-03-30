package com.cashdesh.cashdesh.module.service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileLoggerService {
	@Value("${fib.api.transaction.history}")
	private String TRANSACTION_HISTORY_FILE;
	@Value("${fib.api.cash.balance.history}")
	private String CASH_BALANCES_FILE;

	public void logTransaction(String transactionDetails) {
		try (FileWriter writer = new FileWriter(TRANSACTION_HISTORY_FILE, true)) {
			writer.write(LocalDateTime.now() + " - " + transactionDetails + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logCashBalances(String oldBlanace, String newBalance, String currency) {
		try (FileWriter writer = new FileWriter(CASH_BALANCES_FILE, true)) {
			writer.write("Old balance: " + oldBlanace + " - New balance:" + newBalance + ". Currency:" + currency + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
