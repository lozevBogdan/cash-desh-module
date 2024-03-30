package com.cashdesh.cashdesh.module.init;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.cashdesh.cashdesh.module.entity.CashDesk;

@Component
public class CashDeskInit {
	

    @Bean
	public CashDesk cashDesk() {

		Map<Integer, Integer> //
		denominationsBGN = new HashMap<Integer, Integer>();
		denominationsBGN.put(10, 50);
		denominationsBGN.put(50, 10);

		Map<Integer, Integer> //
		denominationsEUR = new HashMap<Integer, Integer>();
		denominationsEUR.put(10, 100);
		denominationsEUR.put(50, 20);

		return new CashDesk(BigDecimal.valueOf(1000), denominationsBGN, BigDecimal.valueOf(2000), denominationsEUR);
	}

}
