package com.br.tools.salesimport.mapper;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.br.tools.salesimport.domain.LineType;
import com.br.tools.salesimport.domain.Salesman;
import com.br.tools.salesimport.validator.SalesmanLineValidator;

@Component
public class SalesmanMapper implements BaseMapper {
	
	public Salesman map(String[] lineItens) throws Exception {
		
		SalesmanLineValidator.accept(lineItens);
		
		return Salesman.builder()
			.cpf(lineItens[1])
			.name(lineItens[2])
			.salary(BigDecimal.valueOf(Double.valueOf(lineItens[3])))
			.build();
	}

	@Override
	public LineType getLineType() {
		return LineType.SALESMAN;
	}
	
}
