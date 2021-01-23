package com.example.demo.pttgamesale.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.pttgamesale.http.HttpClientTest;
import com.example.demo.util.LoadFile;
import com.example.demo.util.SaveFile;

public class MainController {

	@Value("${pttGameSale.Url}")
	private String url;
	
	@Scheduled(cron = "0 */4 8-23 * * * ?")
	public void getAddNewTitles() {
		Map<String, String> newTitles = new HttpClientTest().getHtml(url);
		Map<String, String> oldTitles = new LoadFile().loadPttGameSaleTitle();
		Map<String, String> addNewTitles = new LinkedHashMap<>();
		
		for(Entry<String, String> newEntry : newTitles.entrySet()) {
			boolean flag = true;
			for(Entry<String, String> oldEntry : oldTitles.entrySet()) {
				if(newEntry.getValue().equals(oldEntry.getValue())) {
					flag = false;
				}
			}
			if(flag) {
				addNewTitles.put(newEntry.getKey(),newEntry.getValue());
			}
		}	
		
		if(addNewTitles.size() > 0) {
			new SaveFile().savePttGameSaleTitle(newTitles);
			System.out.println("新增" + addNewTitles.size() + "筆");
		}else {
			System.out.println("新增" + addNewTitles.size() + "筆");
		}

	}

}
