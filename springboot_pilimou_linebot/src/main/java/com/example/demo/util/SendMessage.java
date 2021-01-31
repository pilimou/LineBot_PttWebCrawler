package com.example.demo.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.linebotreply.Messages;
import com.example.demo.entity.linebotreply.Reply;
import com.example.demo.entity.linebotreply.TextMessages;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SendMessage {
	
	@Value("${lineBot.broadcastUrl}")
	String broadcastUrl;
	
	@Value("${lineBot.callBackReplyUrl}")
	String callBackReplyUrl;
	
	@Autowired
	TextMessages textMessages;

	@Autowired
	Messages messages;
	
	@Autowired
	Reply reply;

	ObjectMapper objectMapper = new ObjectMapper();
	
	//全體廣播
	public void broadcastMessage(String messageType, String message, String channelAccessToken)
			throws RestClientException, JsonProcessingException {
		
		RestTemplate restTemplate = new RestTemplate();
		List<TextMessages> textList = new ArrayList<>();
		HttpHeaders headers = new CommonTools().getHttpHeaders(channelAccessToken);

		textMessages.setType(messageType);
		textMessages.setText(message);

		textList.add(textMessages);

		messages.setMessages(textList);

		restTemplate.exchange(broadcastUrl, HttpMethod.POST, new HttpEntity<>(objectMapper.writeValueAsString(messages),headers),
				String.class);
	}
	
	//回覆text
	public void replyMessage(String messageType, String message, String replyToken, String channelAccessToken) throws RestClientException, JsonProcessingException {
		
		RestTemplate restTemplate = new RestTemplate();
		List<TextMessages> textList = new ArrayList<>();
		HttpHeaders headers = new CommonTools().getHttpHeaders(channelAccessToken);
		
		textMessages.setType(messageType);
		textMessages.setText(message);
		
		textList.add(textMessages);
		
		reply.setMessages(textList);
		reply.setReplyToken(replyToken);
		
		restTemplate.exchange(broadcastUrl, HttpMethod.POST, new HttpEntity<>(objectMapper.writeValueAsString(reply),headers),
				String.class);
	}
}
