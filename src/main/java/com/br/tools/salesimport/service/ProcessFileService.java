package com.br.tools.salesimport.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.br.tools.salesimport.domain.BaseSales;
import com.br.tools.salesimport.domain.LineType;
import com.br.tools.salesimport.mapper.BaseMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProcessFileService {
    
	@Autowired
    private Collection<BaseMapper> mappers;

	@Value("${files-folder.in}")
	private String dataInPath;
	
	public List<BaseSales> processFile(String filePath) throws Exception {
		
		 List<String> lines = Files.lines(Paths.get(filePath))
			.filter(StringUtils::isNotEmpty)
			.collect(Collectors.toList());

		 List<BaseSales> salesList = new ArrayList<BaseSales>();
		 for (String line : lines) {
			 String[] lineItens = null;
				
			try {
				lineItens = line.split(";");
				salesList.add(mapLine(lineItens));
			} catch (Exception e) {
				log.error(String.format("Linha: '%s' do arquivo '%s' inválida. %s", line, filePath, e.getMessage()));
			}
				
		 }
		 
		 return salesList;
		 
	}

	private BaseSales mapLine(String[] lineItens) throws Exception {
		
		if (lineItens.length < 4 || !StringUtils.isNumeric(lineItens[0])) {
			throw new Exception("Indice de linha inválido");
		}
		
		LineType lineType = LineType.valueOfId(lineItens[0]);
		
		return mappers.stream()
	    	.filter(mapper -> lineType.equals(mapper.getLineType()))
	    	.findFirst()
	    	.orElseThrow(() -> new Exception("Falha inesperada, mapper não encontrado."))
	    	.map(lineItens);
		
	}
	
}
