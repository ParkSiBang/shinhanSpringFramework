package com.shinhan.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shinhan.myapp.model.DeptService;

@Controller
public class SampleController {

	@Autowired
	DeptService dService;

	@RequestMapping(value = "/jspTest/test2.do", method = RequestMethod.GET)
	public void f4(Model mdoel) {
		mdoel.addAttribute("dept", dService.selectByIdService(90));
	}

	@RequestMapping(value = "/test3.do", method = RequestMethod.GET)
	public ModelAndView f3() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("dept", dService.selectByIdService(60));
		mv.setViewName("jspTest/test2");
		return mv;
	}

	@RequestMapping(value = "/test2.do", method = RequestMethod.GET)
	public String f2(Model model) {
		model.addAttribute("dept", dService.selectByIdService(60));
		return "jspTest/test2";
	}

	@RequestMapping("/test1.do")
	public String f1(Model dataStore) {
		dataStore.addAttribute("myname", "jin");
		dataStore.addAttribute("score", 99);

		return "jspTest/test1";
	}
}
