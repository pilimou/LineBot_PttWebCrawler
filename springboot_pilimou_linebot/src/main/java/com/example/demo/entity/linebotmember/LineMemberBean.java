package com.example.demo.entity.linebotmember;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="LINE_FOLLOW_MEMBER")
public class LineMemberBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sn;
	private String botId;
	private String type;
	private String followTime;
	private String userId;
	
}
