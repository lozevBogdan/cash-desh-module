package com.cashdesh.cashdesh.module.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cashdesh.cashdesh.module.dto.BalanceDto;
import com.cashdesh.cashdesh.module.dto.ResponceDto;
import com.cashdesh.cashdesh.module.dto.TransactionRequestDto;
import com.cashdesh.cashdesh.module.service.CashOperationService;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("/api/v1/")
@Validated
public class CashOperationController {

	@Autowired
	private CashOperationService cashOperationService;

	@PostMapping("/cash-operation")
	public ResponseEntity<ResponceDto> cashOperation(@Valid @RequestBody TransactionRequestDto request) {
		if ("withdrawal".equals(request.getType())) {
			if (!cashOperationService.withdrawal(request.getAmount(), request.getDenominations(),
					request.getCurrency())) {
				return new ResponseEntity<ResponceDto>(new ResponceDto("Withdrawal failed!", 400),
						HttpStatusCode.valueOf(400));
			}
			return new ResponseEntity<ResponceDto>(new ResponceDto("Withdrawal is successfully executed!", 202),
					HttpStatusCode.valueOf(202));
		} else if ("deposit".equals(request.getType())) {
			cashOperationService.deposit(request.getAmount(), request.getDenominations(), request.getCurrency());
			return new ResponseEntity<ResponceDto>(new ResponceDto("Deposit is successfully deposited!", 202),
					HttpStatusCode.valueOf(202));
		} else if ("deposit".equals(request.getType())) {
		}

		return new ResponseEntity<ResponceDto>(new ResponceDto("Operation not found!", 404),
				HttpStatusCode.valueOf(404));
	}

	@PostMapping("/cash-balance")
	public ResponseEntity<BalanceDto[]> cashBalance() {

		BalanceDto[] //
		balance = cashOperationService.getBalance();

		return new ResponseEntity<BalanceDto[]>(balance, HttpStatusCode.valueOf(200));
	}

}
