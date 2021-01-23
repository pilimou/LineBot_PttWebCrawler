package com.example.demo.entity;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Event {
	private String replyToken;
    private String type;
    private Source source;
    private String timestamp;
    private Message message;
}
