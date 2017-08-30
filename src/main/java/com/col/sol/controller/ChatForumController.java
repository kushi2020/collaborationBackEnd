package com.col.sol.controller;

import java.util.Date;
import java.util.logging.Logger;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.col.sol.model.Message;
import com.col.sol.model.OutputMessage;

@Controller
public class ChatForumController {
	  
	  @MessageMapping("/chat_forum")   ///sendMessage
	  @SendTo("/topic/message")        //receiveMessage
	  public OutputMessage sendMessage(Message message) {
		  System.out.println("Calling the method sendMessage");
	  
		  System.out.printf("---------> Message ID : ",message.getId());
	    return new OutputMessage(message, new Date()); 

	
	  }
}
