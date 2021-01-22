package com.example.demo.unit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveFile {
	String fileDir = "D:/PttGameSaleTitle";
	String fileName = "title.txt";
	
	public void savePttGameSaleTitle(ArrayList<String> newArticleTitles) {
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
			for(String newTitle : newArticleTitles) {
				pw.write(newTitle);
				pw.println();
			}
			pw.flush();			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
