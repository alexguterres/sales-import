package com.br.tools.salesimport.validator;

import org.apache.commons.lang3.StringUtils;

public class CostumerLineValidator {
    
public static void accept(String[] lineItens) throws Exception {
		
		if (lineItens.length != 4) {
			throw new Exception("Número de linhas inválido");
		}
		
		if (StringUtils.isEmpty(lineItens[1])) {
			throw new Exception("Cnpj inválido");
		}
		
		if (!StringUtils.isNumeric(lineItens[1])) {
			throw new Exception(String.format("Cnpj %s inválido", lineItens[1]));
		}
		
		if (StringUtils.isEmpty(lineItens[2])) {
			throw new Exception("Nome do cliente inválido");
		}
		
		if (StringUtils.isEmpty(lineItens[3])) {
			throw new Exception("Ramo de atividade do cliente inválida");
		}
		
	}
	
}
