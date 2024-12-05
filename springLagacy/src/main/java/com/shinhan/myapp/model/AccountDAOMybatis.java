package com.shinhan.myapp.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOMybatis {
	@Autowired
	SqlSession sqlSession;
	
	public int deposit() {
		return sqlSession.update("com.shinhan.account.update1", "112");
	}
	
	public int withdraw() {
		return sqlSession.update("com.shinhan.account.update2");
	}
}
