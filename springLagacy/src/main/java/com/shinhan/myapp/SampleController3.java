package com.shinhan.myapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SampleController3 {

	Logger logger = LoggerFactory.getLogger(SampleController3.class);

	@RequestMapping(value = "/second4.do", params = { "userid=zzilre", "userpw", "!email" })
	public String f3(String userid, String userpw) {
		logger.info("second4 아이디는 " + userid);
		logger.info("second4 비밀번호는 " + userpw);
		
		return "jspTest/second3";
	}

	@RequestMapping(value = "/second3.do", method = RequestMethod.POST)
	public String f2(@RequestParam("userid") String userid2, @RequestParam("userpw") String userpw) {
		logger.info("아이디는 " + userid2);
		logger.info("비밀번호는 " + userpw);

		return "jspTest/second3";
	}

	@RequestMapping(value = { "/second1.do", "/second2.do" }, method = RequestMethod.GET)
	public String f1() {
		return "jspTest/first1";
	}
}
