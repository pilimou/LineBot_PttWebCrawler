package com.example.demo.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.linebotmember.LineMemberBean;

@Repository
public interface LineMemberRepository extends CrudRepository<LineMemberBean, Integer> {
	
	List<LineMemberBean> findByBotIdAndUserId(String botId, String userId);	
	
	Long deleteByBotIdAndUserId(String botId, String userId);
	

}
