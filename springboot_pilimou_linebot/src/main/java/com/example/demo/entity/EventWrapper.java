package com.example.demo.entity;
import lombok.Data; 
import java.util.List;

import org.springframework.stereotype.Component;

@Component
@Data
public class EventWrapper {
	private List<Event> events;  
}
