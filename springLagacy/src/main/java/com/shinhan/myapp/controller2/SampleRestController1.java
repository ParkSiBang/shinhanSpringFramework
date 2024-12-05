package com.shinhan.myapp.controller2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/rest")
public class SampleRestController1 {

	@GetMapping(value = "/test1.do", produces = "text/plain;charset=utf-8")
	public String f1() {
		return "rest방식 연습1";
	}
}
