package com.example.demo.entity.linebotreply;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class TextMessages {
	private String type;
	private String text;
}
