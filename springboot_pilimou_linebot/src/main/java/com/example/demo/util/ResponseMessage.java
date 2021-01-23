package com.example.demo.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Message;
import com.example.demo.entity.Reply;
import com.example.demo.entity.TextMessages;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ResponseMessage {
	
	@Value("${lineBot.callBackEvent}")
	String callBackEvent;

	@Value("${lineBot.channelToken}")
	private String channelToken;
	
	@Autowired
    TextMessages textMessages;
	
	@Autowired
    Reply reply;
	
	
	// Jackson ObjectMapper
    ObjectMapper objectMapper = new ObjectMapper();
	
	public HttpEntity<String> sendMessage(Message message, String replyToken) throws JsonProcessingException{
		List<TextMessages> textList = new ArrayList<>();
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("Content-Type", "application/json");
	     headers.add("Authorization", "Bearer {" + channelToken + "}");
	        
	     if (message.getText().equals(callBackEvent)) {
	    	 
	    	 textMessages.setType("text");
	    	 textMessages.setText("我不想努力了");
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
