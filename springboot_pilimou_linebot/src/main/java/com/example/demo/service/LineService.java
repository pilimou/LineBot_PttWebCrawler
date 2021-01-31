package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.linebotmember.LineMemberBean;
import com.example.demo.repository.LineMemberRepository;


@Service
@Transactional
public class LineService {
	
	@Autowired
	LineMemberRepository lineMemberRepository;
	
	public List<LineMemberBean> findFriend(String botId, String userId){
		List<LineMemberBean> friend = lineMemberRepository.findByBotIdAndUserId(botId, userId);
		if(friend.size() > 0) {
			return friend;
		}else {
			return null;
		}
	}
	
	public void deleteLineMemberByUserId(String botId, String userId) {
		lineMemberRepository.deleteByBotIdAndUserId(botId, userId);
	}

}
