package com.example.demo.pttgamesale.controller;

import com.example.demo.pttgamesale.test.HttpClientTest;
import com.example.demo.unit.SaveFile;

public class MainController {

	public static void main(String[] args) {
		String url = "https://www.ptt.cc/bbs/Gamesale/index.html";
		SaveFile aaa = new SaveFile();
		HttpClientTest httpClientTest = new HttpClientTest();
		httpClientTest.getHtml(url);

	}

}
