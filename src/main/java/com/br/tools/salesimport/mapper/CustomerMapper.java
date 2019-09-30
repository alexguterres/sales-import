package com.br.tools.salesimport.mapper;

import org.springframework.stereotype.Component;

import com.br.tools.salesimport.domain.Customer;
import com.br.tools.salesimport.domain.LineType;
import com.br.tools.salesimport.validator.CostumerLineValidator;

@Component
public class CustomerMapper implements BaseMapper {
	
	public Customer map(String[] lineItens) throws Exception {
		
		CostumerLineValidator.accept(lineItens);
		
		return Customer.builder()
			.cnpj(lineItens[1])
			.name(lineItens[2])
			.activity(lineItens[3])
			.build();
	}

	@Override
	public LineType getLineType() {
		return LineType.CUSTOMER;
	}
	
}
