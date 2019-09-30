package com.br.tools.salesimport.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class SalesmanLineValidator {
    
public static void accept(String[] lineItens) throws Exception {
	
		if (lineItens.length != 4) {
			throw new Exception("Número de linhas inválido");
		}
		
		if (StringUtils.isEmpty(lineItens[1])) {
			throw new Exception("Cpf inválido");
		}
		
		if (!StringUtils.isNumeric(lineItens[1])) {
			throw new Exception(String.format("Cpf %s inválido", lineItens[1]));
		}
		
		if (StringUtils.isEmpty(lineItens[2])) {
			throw new Exception("Nome do vendedor inválido");
		}
		if (!NumberUtils.isParsable(lineItens[3])) {
			throw new Exception("Salário do vendedor inválido");
		}
		
	}
	
}
