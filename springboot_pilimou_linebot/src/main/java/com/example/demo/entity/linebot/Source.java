package com.example.demo.entity.linebot;


import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Source {
	private String type;
    private String userId;
    private String groupId;
    private String roomId;
}
