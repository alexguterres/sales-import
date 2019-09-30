package com.br.tools.salesimport.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.br.tools.salesimport.domain.BaseSales;
import com.br.tools.salesimport.domain.LineType;
import com.br.tools.salesimport.domain.Sale;

@Service
public class GenerateSummarySalesService {
    
	@Value("${files-folder.out}")
	private String dataOutPath;
	
	public void generate(List<BaseSales> salesList, String filePath) throws IOException  {
		if (!Files.exists(Paths.get(getFilePath())))
			Files.createDirectory(Paths.get(getFilePath()));
		
		Files.write(Paths.get(getFilePath() + getFileName(filePath)), generateFileContent(salesList));
	}

	private byte[] generateFileContent(List<BaseSales> salesList) {
		StringBuilder content = new StringBuilder();
		
		content.append(generateAmountCustomer(salesList));
		content.append(generateAmountSalesman(salesList));
		content.append(generateHighestSaleId(salesList));
		content.append(generateLowesSalesmanName(salesList));
		
		return content.toString().getBytes();
	}

	private Object generateLowesSalesmanName(List<BaseSales> salesList) {
		
		Map<String, BigDecimal> collect = salesList.stream()
			.filter(sale -> sale.getLineType().equals(LineType.SALE))
			.map(sale -> ((Sale)sale))
			.collect(Collectors.groupingBy(Sale::getSalesmanName, 
				Collectors.reducing(BigDecimal.ZERO, Sale::getSalePrice, BigDecimal::add)));
		
		if (!collect.isEmpty()) {
			String name = collect.entrySet().stream()
				.min(Comparator.comparing(Entry::getValue))
				.get()
				.getKey();
			
			return "4. Nome do Vendedor que menos vendeu: " + name + StringUtils.LF;
		}
		
		return StringUtils.EMPTY;

	}

	private String generateHighestSaleId(List<BaseSales> salesList) {
		
		Optional<Long> highestPriceOptional = salesList.stream()
			.filter(sale -> sale.getLineType().equals(LineType.SALE))
			.map(sale -> ((Sale)sale))
			.max(Comparator.comparing(Sale::getSalePrice))
			.map(Sale::getId);
		
		if (highestPriceOptional.isPresent()) {
			return "3. ID da Venda de valor mais alto: " + highestPriceOptional.get() + StringUtils.LF;
		}
		
		return StringUtils.EMPTY;
		
	}

	private String generateAmountSalesman(List<BaseSales> salesList) {

		if (salesList.isEmpty()) {
			return StringUtils.EMPTY;
		}
		
		long amountSalesman = salesList.stream()
			.filter(sale -> sale.getLineType().equals(LineType.SALESMAN))
			.count();
		
		return "2. Quantidade de Vendedores: " +  amountSalesman + StringUtils.LF;
	}

	private String generateAmountCustomer(List<BaseSales> salesList) {
		
		if (salesList.isEmpty()) {
			return StringUtils.EMPTY;
		}
		
		long amountCustomers = salesList.stream()
			.filter(sale -> sale.getLineType().equals(LineType.CUSTOMER))
			.count();
		
		return "1. Quantidade de Clientes: " + amountCustomers + StringUtils.LF;
		
	}

	private String getFilePath() {
		Path currentRelativePath = Paths.get("");
		String absolutePath = currentRelativePath.toAbsolutePath().toString();
		return String.format("%s%s%s%s", absolutePath, File.separator, dataOutPath, File.separator);
	}

	private Object getFileName(String filePath) {
		int indexOfName = filePath.lastIndexOf(File.separator);
		return filePath.substring(indexOfName, filePath.length()) + ".proc";
	}
	
}
