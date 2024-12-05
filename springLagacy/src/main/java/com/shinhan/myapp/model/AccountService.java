package com.shinhan.myapp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
	@Autowired
	AccountDAOMybatis dao;
	
	public void transferService() {
		int ret1 = dao.deposit();
		int ret2 = dao.withdraw();
	}
}
