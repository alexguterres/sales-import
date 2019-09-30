package com.br.tools.salesimport.mapper;

import com.br.tools.salesimport.domain.BaseSales;
import com.br.tools.salesimport.domain.LineType;

public interface BaseMapper {
	
	abstract BaseSales map(String[] line) throws Exception;

	abstract LineType getLineType();
	
}
