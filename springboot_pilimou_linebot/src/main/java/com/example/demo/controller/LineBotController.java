package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Event;
import com.example.demo.entity.EventWrapper;
import com.example.demo.util.ResponseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class LineBotController {
	
	@Value("${lineBot.callBackEvent}")
	String callBackEvent;
	
	@Value("${lineBot.callBackReply}")
	String reply;
	
	@Autowired
    ResponseMessage responseMessage;
	
	@Autowired
    Event event;
	
	// Jackson ObjectMapper
    ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("/callback")
	public ResponseEntity<String> callback(@RequestBody EventWrapper events) throws RestClientException, JsonProcessingException{
		RestTemplate restTemplate = new RestTemplate();
		String replyToken = null;
		events.getEvents();
		 for(Event event : events.getEvents()){ 
			 replyToken = event.getReplyToken();
			 this.event = event;
	     }	
		 
		if(event.getMessage().getText().equals(callBackEvent)) {
			ResponseEntity<String> response = restTemplate.exchange(reply, HttpMethod.POST,responseMessage.sendMessage(event.getMessage(), replyToken),String.class);		
			return response;
		}else {
			return new ResponseEntity<String>(HttpStatus.OK);
		}
	}
}
