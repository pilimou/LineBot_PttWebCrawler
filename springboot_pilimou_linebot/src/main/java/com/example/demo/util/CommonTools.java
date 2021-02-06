package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.HttpHeaders;

public class CommonTools {
	
	//轉時間格式
	public String getStringDate(String timeMillies) {
		Date date = new Date();
		date.setTime(Long.parseLong(timeMillies));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
		return sdf.format(date);
	}
	
	//設定LineBot Headers
	public HttpHeaders getHttpHeaders(String channelAccessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("Authorization", "Bearer {" + channelAccessToken + "}");
		return headers;
	}
	
	
	//比對傳進來的MAP和比對條件字串
	public String containsString(Map<String, String> newTitles,String[] containsStrs) {
		
		String messagesText = "";		
		
		int i;
		for(Entry<String, String> newTitle : newTitles.entrySet()) {
			i = 0;
			for (String containsStr : containsStrs) {
				newTitle.getValue().contains(containsStr);
				i++;
			}
			if(i == containsStrs.length) {
				messagesText += newTitle.getKey() + "\n" + newTitle.getValue() + "\n";	
			}
		}
		
		return messagesText;
	}
}
