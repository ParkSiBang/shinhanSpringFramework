package com.shinhan.myapp.controller2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.myapp.emp.EmpDTO;
import com.shinhan.myapp.emp.EmpService;

@RestController
@RequestMapping("/restqqq")
public class SampleRestController2 {

	@Autowired
	EmpService empService;

	@GetMapping(value = "/test2.do", produces = "text/plain;charset=utf-8")
	public String f1() {
		return "rest방식 연습2(@RestController)";
	}

	@GetMapping(value = "/emplist.do", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EmpDTO> f2() {
		List<EmpDTO> empList = empService.selectAllService();
		return empList;
	}

	@GetMapping(value = "/empDetail.do/{empid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EmpDTO f3(@PathVariable int empid) {
		return empService.selectByIdService(empid);
	}

}
