package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.example.demo.crawler.pttgamesale.controller.CrawlerController;
import com.example.demo.entity.linebotreply.Events;
import com.example.demo.util.CommonEvent;
import com.example.demo.util.CommonTools;
import com.example.demo.util.SendMessage;
import com.example.demo.util.VerificationLine;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class LineBotController extends CommonEvent {

	@Value("${lineBot.giveUp.callBackEvent}")
	private String callBackEvent;

	@Value("${lineBot.giveUp.channelSecret}")
	private String channelSecret;
	
	@Value("${lineBot.giveUp.channelToken}")
	private String channelToken;
	
	@Value("${lineBot.giveUp.keyWord}")
	private String[] keyWord;
	
	@Autowired
	CommonTools commonTools;
	
	@Autowired
	SendMessage sendMessage;

	@Autowired
	VerificationLine verificationLine;
	
	@Autowired
	CrawlerController pttGameSale;

	// Jackson ObjectMapper
	ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("/receiveEvent")
	public ResponseEntity<String> receiveEvent(@RequestBody String requestBody,
			@RequestHeader("X-Line-Signature") String line_headers)
			throws RestClientException, JsonProcessingException {
		System.out.println(requestBody);
		// ------------------接收驗證------------------

		if (verificationLine.checkFromLine(requestBody, line_headers, channelSecret)) {

			// ------------------判斷事件------------------
			triggerEvent(requestBody, "giveUp");
			
		}

		return new ResponseEntity<String>(HttpStatus.OK);

	}
	
	
	//Override message是text時
	@Override
	public void messageEventText(Events event, String botId) throws RestClientException, JsonProcessingException {
		super.messageEventText(event, botId);
		if (event.getMessage().getText().equals(callBackEvent)) {
			String messagesText = null;
			if (event.getMessage().getText().equals("NS")) {
				messagesText = "NNNNNN";
				sendMessage.replyMessage("text", messagesText, channelToken, event.getReplyToken());				
			}			
		}
		
	}


	//廣播排程
	@Scheduled(cron = "0 */20 * * * ?")
	public void getNewArticle() throws RestClientException, JsonProcessingException {
		String messagesText = commonTools.containsString(pttGameSale.getAddNewTitles(), keyWord);
		if (messagesText.length() > 0) {
			sendMessage.broadcastMessage("text", messagesText, channelToken);
		}
	}
}
