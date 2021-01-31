package com.example.demo.crawler.pttgamesale.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.crawler.pttgamesale.file.LoadFile;
import com.example.demo.crawler.pttgamesale.file.SaveFile;
import com.example.demo.crawler.pttgamesale.html.HttpClientTest;

@Component
public class CrawlerController {

	@Value("${crawler.pttGameSale.url}")
	private String url;
	
	@Autowired
	HttpClientTest httpClientTest;
	
	@Autowired
	LoadFile loadFile;
	
	@Autowired
	SaveFile saveFile;
	
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
		}else {
			addNewTitles = null;
		}
		return addNewTitles;
	}

}
