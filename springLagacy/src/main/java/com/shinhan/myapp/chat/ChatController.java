package com.shinhan.myapp.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

	@GetMapping("/chat.do")
	public String chat() {
		return "chat/chatting";
	}
}
