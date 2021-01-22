package com.example.demo.unit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LoadFile {
	String fileDir = "D:/PttGameSaleTitle";
	String fileName = "title.txt";
	
	public ArrayList<String> loadPttGameSaleTitle() {
		ArrayList<String> oldArticleTitles = new ArrayList<String>();
		File file = new File(fileDir + "/" + fileName);
		
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
				oldArticleTitles.add(oldTitle);
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}	
		
		
		return oldArticleTitles;
	}

}
