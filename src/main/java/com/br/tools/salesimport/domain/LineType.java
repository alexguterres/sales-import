package com.br.tools.salesimport.domain;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LineType {

	CUSTOMER("002"),
	SALE("003"),
	SALESMAN("001");
	
	@Getter
	private String idLine;
	
	public static LineType valueOfId(String idLine) {
		return Arrays.stream(LineType.values())
			.filter(lineType -> lineType.getIdLine().equals(idLine))
			.findFirst()
			.get();
	}
}
