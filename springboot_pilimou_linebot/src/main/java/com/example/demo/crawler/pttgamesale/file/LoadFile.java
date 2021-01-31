package com.example.demo.crawler.pttgamesale.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class LoadFile {
	
	@Value("${crawler.pttGameSale.fileDir}")
	private String fileDir;
	
	@Value("${crawler.pttGameSale.fileName}")
	private String fileName;
	
	public Map<String, String> loadPttGameSaleTitle() {
		Map<String, String> oldArticleTitles = new LinkedHashMap<>();
		File file = new File(fileDir + "/" + fileName);
		System.out.println(fileDir + "/" + fileName);
		try {
			if(!file.exists()) {
				new File(fileDir).mkdir();
				file.createNewFile();
			}	
		}catch(IOException e) {
			e.printStackTrace();
		}	
		
		try(
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
		){	
			String oldTitle = "";
			for(int i = 0; (oldTitle = br.readLine()) != null; i++) {
				String key = oldTitle.split(":;:")[0];
				String value = oldTitle.split(":;:")[1];
				oldArticleTitles.put(key, value);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}	
		
		return oldArticleTitles;
	}

}
