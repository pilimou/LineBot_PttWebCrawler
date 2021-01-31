package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
}
