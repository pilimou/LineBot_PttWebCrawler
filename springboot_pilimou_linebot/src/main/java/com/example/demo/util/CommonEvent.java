package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClientException;

import com.example.demo.entity.linebotmember.LineMemberBean;
import com.example.demo.entity.linebotreply.Events;
import com.example.demo.entity.linebotreply.EventWrapper;
import com.example.demo.repository.LineMemberRepository;
import com.example.demo.service.LineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonEvent {

	@Value("${lineBot.callBackReplyUrl}")
	private String callBackreply;

	@Autowired
	LineMemberRepository lineMemberRepository;

	@Autowired
	LineService lineService;

	// Jackson ObjectMapper
	ObjectMapper objectMapper = new ObjectMapper();

	// ------------------事件------------------
	public void triggerEvent(String requestBody, String botId) throws JsonMappingException, JsonProcessingException {

		// 把requestBody(Json)轉成物件
		EventWrapper events = objectMapper.readValue(requestBody, EventWrapper.class);
		Events event = null;
		for (Events evt : events.getEvents()) {
			event = evt;
		}

		String eventType = null;
		if (event != null) {
			
			if (event.getType() != null) {
				
				eventType = event.getType();
				
				// 被加好友時
				if (eventType.equals("follow"))
					followEvent(event, botId);
				
				// 被封鎖時
				if (eventType.equals("unfollow"))
					unFollowEvent(event, botId);
				
				// message
				if (eventType.equals("message"))
					messageEvent(event, botId);
				
				// 機器人被加入群組/聊天室
				if (eventType.equals("join"))
					joinEvent(event, botId);
				
				// 機器人已離開群組/聊天室
				if (eventType.equals("leave"))
					leaveEvent(event, botId);
				
				// 使用者加入群組/聊天室
				if (eventType.equals("memberJoined"))
					memberJoinedEvent(event, botId);
				
				// 使用者離開群組/聊天室
				if (eventType.equals("memberLeft"))
					memberLeftEvent(event, botId);
				
			}
		}
	}

//-------------實做trigger事件-----------

	// 被加好友時
	public void followEvent(Events event, String botId) {

		if (lineService.findFriend(botId, event.getSource().getUserId()) != null) {

			lineMemberRepository.save(new LineMemberBean(null, botId, event.getType(),
					new CommonTools().getStringDate(event.getTimestamp()), event.getSource().getUserId()));
			System.out.println("save.ok");
		}
	}

	// 被封鎖時
	public void unFollowEvent(Events event, String botId) {

		lineService.deleteLineMemberByUserId(botId, event.getSource().getUserId());
	}

	// 機器人被加入群組/聊天室
	public void joinEvent(Events event, String botId) {

	}

	// 機器人已離開群組/聊天室
	public void leaveEvent(Events event, String botId) {

	}

	// 使用者加入群組/聊天室
	public void memberJoinedEvent(Events event, String botId) {

	}

	// 使用者離開群組/聊天室
	public void memberLeftEvent(Events event, String botId) {

	}

	// message
	public void messageEvent(Events event, String botId) throws RestClientException, JsonProcessingException {

		// 如果message是文字
		if (event.getMessage().getType().equals("text"))
			messageEventText(event, botId);

		// 如果message是照片
		if (event.getMessage().getType().equals("image"))
			messageEventImage(event, botId);
		
		// 如果message是影片
		if (event.getMessage().getType().equals("video"))
			messageEventVideo(event, botId);
		
		// 如果message是audio
		if (event.getMessage().getType().equals("audio"))
			messageEventAudio(event, botId);
		
		// 如果message是檔案
		if (event.getMessage().getType().equals("file"))
			messageEventFile(event, botId);
		
		// 如果message是location
		if (event.getMessage().getType().equals("location"))
			messageEventLocation(event, botId);
		
		// 如果message是貼圖
		if (event.getMessage().getType().equals("sticker"))
			messageEventSticker(event, botId);

	}

//-----------------實作message事件---------------------------	

	public void messageEventText(Events event, String botId) throws RestClientException, JsonProcessingException {

	}
	
	public void messageEventImage(Events event, String botId) {

	}
	
	public void messageEventVideo(Events event, String botId) {

	}
	
	public void messageEventAudio(Events event, String botId) {

	}
	
	public void messageEventFile(Events event, String botId) {

	}
	
	public void messageEventLocation(Events event, String botId) {

	}
	
	public void messageEventSticker(Events event, String botId) {

	}
}
