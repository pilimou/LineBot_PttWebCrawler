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

	public void triggerEvent(String requestBody, String botId) throws JsonMappingException, JsonProcessingException {

		// 把requestBody(Json)轉成物件
		EventWrapper events = objectMapper.readValue(requestBody, EventWrapper.class);
		Events event = null;
		for (Events evt : events.getEvents()) {
			event = evt;
		}

		// ------------------事件------------------
		String eventType = null;

		if (event.getType() != null) {

			eventType = event.getType();

			// 被加好友時
			if (eventType.equals("follow")) {

				followEvent(event, botId);
			}

			// 被封鎖時
			if (eventType.equals("unfollow")) {

				unFollowEvent(event, botId);
			}

			// message
			if (eventType.equals("message")) {
				
				messageEvent(event, botId);
			}

			// 機器人被加入群組/聊天室
			if (eventType.equals("join")) {

			}

			// 機器人已離開群組/聊天室
			if (eventType.equals("join")) {

			}

			// 使用者加入群組/聊天室
			if (eventType.equals("memberJoined")) {

			}

			// 使用者離開群組/聊天室
			if (eventType.equals("memberJoined")) {

			}

		}
	}

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
	
	// message
	public void messageEvent(Events event, String botId) throws RestClientException, JsonProcessingException {
		
	}
}
