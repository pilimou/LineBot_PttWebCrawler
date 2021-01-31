package com.example.demo.entity.linebotreply;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Component
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Events {
	private String replyToken;
    private String type;
    private String mode;
    private Source source;
    private String timestamp;
    //接收
    private Message message;
    //傳送
    private Messages messages;
    private Postback postback;
}
