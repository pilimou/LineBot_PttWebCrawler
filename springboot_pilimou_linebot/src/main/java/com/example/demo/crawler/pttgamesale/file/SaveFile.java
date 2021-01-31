package com.example.demo.crawler.pttgamesale.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SaveFile {
	
	@Value("${crawler.pttGameSale.fileDir}")
	private String fileDir;
	
	@Value("${crawler.pttGameSale.fileName}")
	private String fileName;
	
	public void savePttGameSaleTitle(Map<String,String> map) {
		Map<String,String> newArticleTitles = map;
		File file = new File(fileDir + "/" + fileName);
		
		try {
			if(!file.exists()) {
				new File(fileDir).mkdir();
				file.createNewFile();
			}	
		}catch(IOException e) {
			e.printStackTrace();
		}	
        
		try (
			FileOutputStream fos = new FileOutputStream(file);
			PrintWriter pw = new PrintWriter(fos);
		){
			for(Entry<String, String> entry : newArticleTitles.entrySet()) {
				pw.write(entry.getKey() + ":;:" + entry.getValue());
				pw.println();
			}
			pw.flush();	
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
