package com.example.demo.pttgamesale.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.pttgamesale.http.HttpClientTest;
import com.example.demo.util.LoadFile;
import com.example.demo.util.SaveFile;

@Component
public class MainController {

	@Value("${pttGameSale.Url}")
	private String url;
	
	@Autowired
	HttpClientTest httpClientTest;
	
	@Autowired
	LoadFile loadFile;
	
	@Autowired
	SaveFile saveFile;
	
	@Scheduled(cron = "0 */6 8-23 * * ?")
	public Map<String, String> getAddNewTitles() {
		Map<String, String> newTitles = httpClientTest.getHtml(url);
		Map<String, String> oldTitles = loadFile.loadPttGameSaleTitle();
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
			saveFile.savePttGameSaleTitle(newTitles);
		}
		return newTitles;
	}

}
