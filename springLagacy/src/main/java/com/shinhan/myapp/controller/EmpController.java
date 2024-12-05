package com.shinhan.myapp.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.myapp.emp.EmpDTO;
import com.shinhan.myapp.emp.EmpService;
import com.shinhan.myapp.model.AccountService;
import com.shinhan.myapp.model.DeptService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/emp")
@RequiredArgsConstructor
public class EmpController {

	@Autowired
	EmpService empService;

	@Autowired
	DeptService deptService;

	final AccountService accountService;

	@ResponseBody
	@GetMapping(value = "/transfer.do", produces = "text/plain;charset=utf-8")
	public String transfer() {
		accountService.transferService();
		return "이체 서비스 완료";
	}

	@GetMapping("/listByArray.do")
	public String listByArray(@RequestParam(value = "deptAry[]") Integer[] ary, Model model) {
		model.addAttribute("empDatas", empService.selectByArray(Arrays.asList(ary)));
		return "emp/empListTable";
	}

	@GetMapping("/listByJobJoin.do")
	public String listByJobJoin(String job_id, Model model) {
		model.addAttribute("empDatas", empService.selectByJobJoin(job_id));
		return "emp/empListTable2";
	}

	@GetMapping("/listByDept.do")
	public String listByDept(int deptid, Model model) {
		model.addAttribute("empDatas", empService.selectByDept(deptid));
		return "emp/empListTable";
	}

	@GetMapping("/listByJob.do")
	public String listByJob(String job_id, Model model) {
		model.addAttribute("empDatas", empService.selectByJob(job_id));
		return "emp/empListTable";
	}

	@GetMapping("/listBySalary.do")
	public String listBySalary(double salary, Model model) {
		model.addAttribute("empDatas", empService.selectBySalary(salary));
		return "emp/empListTable";
	}

	@GetMapping("/empList.do")
	public String selectAll(Model model, HttpServletRequest request) {
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if (map != null) {
			model.addAttribute("resultMessage", map.get("resultMessage"));
		}

		model.addAttribute("jobList", empService.selectAllJobService());
		model.addAttribute("deptList", deptService.selectAllService());

		return "emp/empList";
	}

	@GetMapping("/list2.do")
	public String selectCondition(Model model, @RequestParam Map<String, Object> map) {
		String chk = (String) map.get("chk");
		if (chk.equals("true"))
			map.put("hdate", "1900-01-01");
		List<EmpDTO> empList = empService.selectByCondition(map);
		model.addAttribute("empDatas", empList);

		return "emp/empListTable";
	}

	@GetMapping("empInsert.do")
	public String insertGet(Model model) {
		model.addAttribute("jobList", empService.selectAllJobService());
		model.addAttribute("deptList", deptService.selectAllService());
		model.addAttribute("managerList", empService.selectAllService());

		return "emp/empInsert";
	}

	@PostMapping("empInsert.do")
	public String insertPost(EmpDTO emp, RedirectAttributes attr) {
		int result = empService.insertService(emp);
		attr.addFlashAttribute("resultMessage", result > 0 ? "입력성공" : "입력실패");

		return "redirect:empList.do";
	}

	@GetMapping("/detail.do")
	public String detailGet(int empid, Model model) {
		model.addAttribute("jobList", empService.selectAllJobService());
		model.addAttribute("deptList", deptService.selectAllService());
		model.addAttribute("managerList", empService.selectAllService());
		model.addAttribute("empInfo", empService.selectByIdService(empid));

		return "emp/empDetail";
	}

	@PostMapping("/detail.do")
	public String detailPost(EmpDTO emp, RedirectAttributes attr) {
		int result = empService.updateService(emp);
		attr.addFlashAttribute("resultMessage", result > 0 ? "수정성공" : "수정실패");

		return "redirect:empList.do";
	}

	@RequestMapping(value = "/empDelete.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(int empid, RedirectAttributes attr) {
		int result = empService.deleteService(empid);
		attr.addFlashAttribute("resultMessage", result > 0 ? "삭제성공" : "삭제실패");

		return "redirect:empList.do";
	}
}
