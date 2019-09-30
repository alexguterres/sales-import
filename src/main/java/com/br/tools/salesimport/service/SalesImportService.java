package com.br.tools.salesimport.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.br.tools.salesimport.domain.BaseSales;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SalesImportService {
	
	@Autowired
    private ProcessFileService processFileService;
    
    @Autowired
    private GenerateSummarySalesService generateSummarySalesService;
	
    @Value("${files-folder.in}")
    private String dataInPath;
    
    @Value("${project.not-validate-processed-files}")
    private boolean notValidateProcessedFiles;
    
    private static List<String> processedFiles = new ArrayList<String>();
    
    @Scheduled(fixedDelay=10000)
	public void importFiles() {
    	log.info("Start processing");
		
		try (Stream<Path> walk = Files.walk(Paths.get(getFilesInPath()))) {

			List<String> resultFiles = walk.filter(Files::isRegularFile)
				.filter(path -> path.toString().toLowerCase().endsWith(".dat"))
				.map(path -> path.toString())
				.collect(Collectors.toList());
			
			if (processFile(resultFiles)) {
				processedFiles = new ArrayList<String>();
				
				for (String filePath : resultFiles) {
					final List<BaseSales> processedFile = processFileService.processFile(filePath);
					generateSummarySalesService.generate(processedFile, filePath);
					addProcessedFile(filePath);
				}
				
				log.info("End of processing");
			} else {
				log.info("Processing not required");
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	private void addProcessedFile(String filePath) {
		if (!notValidateProcessedFiles) 
			processedFiles.add(filePath);
	}

	private boolean processFile(List<String> resultFiles) {
		return notValidateProcessedFiles || !processedFiles.equals(resultFiles);
	}

	private String getFilesInPath() throws Exception {
		try {
			Path currentRelativePath = Paths.get("");
			String absolutePath = currentRelativePath.toAbsolutePath().toString();
			return String.format("%s%s%s%s", absolutePath, File.separator, dataInPath, File.separator);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
