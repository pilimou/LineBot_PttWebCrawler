package com.example.demo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.example.demo.entity.linebot.Message;
import com.example.demo.entity.linebot.Reply;
import com.example.demo.entity.linebot.TextMessages;
import com.example.demo.pttgamesale.controller.MainController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ResponseMessage {
	
	@Value("${lineBot.channelToken}")
	private String channelToken;
	
	@Autowired
    TextMessages textMessages;
	
	@Autowired
    Reply reply;
	
	@Autowired
	MainController pttMainController;
	
	// Jackson ObjectMapper
    ObjectMapper objectMapper = new ObjectMapper();
	
	public HttpEntity<String> sendMessage(Message message, String replyToken) throws JsonProcessingException{
		List<TextMessages> textList = new ArrayList<>();
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Content-Type", "application/json");
	     headers.add("Authorization", "Bearer {" + channelToken + "}");
	        
	     if (message.getText().equals("NS")) {
	    	
	    	 textMessages.setType("text");
	    	 textMessages.setText("哈哈哈哈哈哈哈");
	    	 textList.add(textMessages);
	    	 
	    	 reply.setReplyToken(replyToken);
	         reply.setMessages(textList);
	         
	         return new HttpEntity<>(objectMapper.writeValueAsString(reply), headers);
	     }else {
	    	 
	    	 textMessages.setType("text");
	    	 textMessages.setText("無法辨識");
	    	 textList.add(textMessages);

	    	 reply.setReplyToken(replyToken);
	    	 reply.setMessages(textList);

	         return new HttpEntity<>(objectMapper.writeValueAsString(reply), headers);
	        }
		    
        
	}
}
