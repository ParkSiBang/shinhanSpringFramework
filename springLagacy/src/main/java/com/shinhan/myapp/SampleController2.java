package com.shinhan.myapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/jspTest")
public class SampleController2 {

	@GetMapping("/first1.do")
	public void f1() {

	}

	@RequestMapping("/first2.do")
	public void f2() {

	}

	@RequestMapping(value = "/first3.do", method = RequestMethod.GET)
	public void f3() {

	}
}
