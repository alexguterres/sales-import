package com.br.tools.salesimport.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class SaleLineValidator {
    
	public static void accept(String[] lineItens) throws Exception {
		
		if (lineItens.length != 6) {
			throw new Exception("Número de linhas inválido");
		}
		
		if (!StringUtils.isNumeric(lineItens[1])) {
			throw new Exception("Id de venda inválido");
		}
		
		if (!StringUtils.isNumeric(lineItens[2])) {
			throw new Exception("Id de item inválido");
		}
		
		if (!StringUtils.isNumeric(lineItens[3])) {
			throw new Exception("Quantidade de itens inválida");
		}
		
		if (!NumberUtils.isParsable(lineItens[4])) {
			throw new Exception("Valor do itens inválido");
		}
		
		if (StringUtils.isEmpty(lineItens[5])) {
			throw new Exception("Nome do vendedor inválido");
		}
		
	}
	
}
