package com.shinhan.myapp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.w3c.dom.Attr;

import com.shinhan.myapp.model.DeptService;
import com.shinhan.myapp.vo.DeptDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DeptController {

	Logger logger = LoggerFactory.getLogger(DeptController.class);

	@Autowired
	DeptService dService;

	// 삭제
	@RequestMapping(value = "/dept/delete.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(int deptid, RedirectAttributes attr) {
		int result = dService.deleteService(deptid);
		String message = "삭제건수: " + result;
		attr.addFlashAttribute("resultMessage", message);

		return "redirect:/dept/deptList.do";
	}

	// 수정
	@PostMapping("/dept/deptDetail.do")
	public String detailPost(DeptDTO dept, RedirectAttributes attr) {
		int result = dService.updateService(dept);
		String message = "수정건수: " + result;
		attr.addFlashAttribute("resultMessage", message);

		return "redirect:/dept/deptDetail.do";
	}

	// 입력
	@GetMapping("/dept/deptInsert.do")
	public String insertGet(DeptDTO dept) {

		return "dept/deptInsert";
	}

	@PostMapping("/dept/deptInsert.do")
	public String insertPost(DeptDTO dept, RedirectAttributes attr) {
		int result = dService.insertService(dept);
		String message = "입력건수: " + result;
		logger.info(message);
		attr.addFlashAttribute("resultMessage", message);

		return "redirect:/dept/deptList.do";
	}

	// 조회
	@RequestMapping("/dept/deptList.do")
	public String f1(Model model, HttpServletRequest request) {
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if (map != null) {
			String message = (String) map.get("resultMessage");
			model.addAttribute("result", message);
		}

		List<DeptDTO> deptList = dService.selectAllService();
		model.addAttribute("deptList", deptList);

		return "dept/deptList";
	}

	@GetMapping("/dept/deptDetail.do")
	public String detail(int deptid, Model model) {
		model.addAttribute("deptInfo", dService.selectByIdService(deptid));

		return "dept/deptDetail";
	}
}
