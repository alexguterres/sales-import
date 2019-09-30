package com.br.tools.salesimport.domain;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Salesman extends BaseSales {

	private final String cpf;
	private final String name;
	private final BigDecimal salary;
	
	@Override
	public LineType getLineType() {
		return LineType.SALESMAN;
	}
	
}
