package com.br.tools.salesimport.domain;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Sale extends BaseSales {

	private final Long id;
	private final Long idItem;
	private final int itemAmount;
	private final BigDecimal itemPrice;
	private final String salesmanName;
	
	@Override
	public LineType getLineType() {
		return LineType.SALE;
	}
	
	public BigDecimal getSalePrice() {
		return getItemPrice().multiply(BigDecimal.valueOf(getItemAmount()));
	}
	
}
