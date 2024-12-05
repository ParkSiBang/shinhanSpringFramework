package com.shinhan.myapp.controller2;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shinhan.myapp.vo.ParamVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/friday")
@Slf4j
public class FridayController {

	@RequestMapping("/one.do")
	public void f1() {

	}

	@GetMapping("/two.do")
	public String f2(Model model, @RequestParam Map<String, String> map, ParamVO param, String username,
			@RequestParam(value = "userid", required = false) Integer userid2) {
		log.info("userid: " + userid2);

		return "redirect:/dept/deptList.do";
	}

}
