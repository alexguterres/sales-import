package com.br.tools.salesimport.mapper;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.br.tools.salesimport.domain.LineType;
import com.br.tools.salesimport.domain.Sale;
import com.br.tools.salesimport.validator.SaleLineValidator;


@Component
public class SaleMapper implements BaseMapper {
	
	public Sale map(String[] lineItens) throws Exception {
		
		SaleLineValidator.accept(lineItens);
		
		return Sale.builder()
			.id(Long.valueOf(lineItens[1]))
			.idItem(Long.valueOf(lineItens[2]))
			.itemAmount(Integer.valueOf(lineItens[3]))
			.itemPrice(BigDecimal.valueOf(Double.valueOf(lineItens[4])))
			.salesmanName(lineItens[5])
			.build();
	}

	@Override
	public LineType getLineType() {
		return LineType.SALE;
	}
	
}
