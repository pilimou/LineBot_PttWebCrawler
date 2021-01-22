package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Event;
import com.example.demo.entity.EventWrapper;

@RestController
public class LineBotController {
//	@RequestBody EventWrapper events
	@PostMapping("/callback")
	public void callback(@RequestBody String events){
		System.out.println(events);
//		events.getEvents();
//		 for(Event event : events.getEvents()){ 
//	            System.out.println(event.getReplyToken()); 
//	            System.out.println(event.getMessage().getText());
//	        }	
//		 
		 
	}
}
