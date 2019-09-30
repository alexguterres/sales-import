package com.br.tools.salesimport.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Customer extends BaseSales {

	private final String cnpj;
	private final String name;
	private final String activity;
	
	@Override
	public LineType getLineType() {
		return LineType.CUSTOMER;
	}
	
}
