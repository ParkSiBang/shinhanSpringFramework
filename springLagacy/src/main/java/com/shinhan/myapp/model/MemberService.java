package com.shinhan.myapp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.myapp.vo.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	MemberDAO mDAO;
	
	public MemberDTO loginService(String mid, String mpass) {
		
		return mDAO.login(mid, mpass);
	}
}
