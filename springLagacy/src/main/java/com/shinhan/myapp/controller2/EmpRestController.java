package com.shinhan.myapp.controller2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.myapp.emp.EmpDTO;
import com.shinhan.myapp.emp.EmpService;

@RestController
@RequestMapping("/rest")
public class EmpRestController {

	@Autowired
	EmpService empService;

	@DeleteMapping(value = "/empDelete.do/{empid}", produces = "text/plain;charset=utf-8")
	public String delete(@PathVariable int empid) {
		int result = empService.deleteService(empid);
		return result > 0 ? "delete성공" : "delete실패";
	}

	@PutMapping(value = "/empUpdate.do", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/plain;charset=utf-8")
	public String update(@RequestBody EmpDTO emp) {
		int result = empService.updateService(emp);
		return result > 0 ? "update성공" : "update실패";
	}

	@PostMapping(value = "/empInsert.do", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/plain;charset=utf-8")
	public String insert(@RequestBody EmpDTO emp) {
		int result = empService.insertService(emp);
		return result > 0 ? "insert성공" : "insert실패";
	}

	@GetMapping(value = "/empMap.do", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Integer, EmpDTO> f4() {
		Map<Integer, EmpDTO> map = new HashMap<>();
		List<EmpDTO> empList = empService.selectAllService();
		empList.forEach(emp -> {
			map.put(emp.getEmployee_id(), emp);
		});
		return map;
	}

	@GetMapping(value = "/empDetail.do/{empid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EmpDTO f3(@PathVariable int empid) {
		return empService.selectByIdService(empid);
	}

	@GetMapping(value = "/emplist.do", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EmpDTO> f2() {
		List<EmpDTO> empList = empService.selectAllService();
		return empList;
	}

	@GetMapping(value = "/test2.do", produces = "text/plain;charset=utf-8")
	public String f1() {
		EmpDTO emp = empService.selectByIdService(100);
		return "rest방식 연습2(@RestController): " + emp.getFirst_name();
	}
}
