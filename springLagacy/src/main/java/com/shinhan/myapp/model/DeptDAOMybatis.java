package com.shinhan.myapp.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.vo.DeptDTO;

@Repository("deptMybatis")
public class DeptDAOMybatis implements DeptDAOInterface {

	@Autowired
	SqlSession sqlSession;

	String nameSpace = "com.shinhan.dept.";
	int result;

	public List<DeptDTO> selectAll() {
		List<DeptDTO> deptList = sqlSession.selectList(nameSpace + "selectAll");
		return deptList;
	}

	public DeptDTO selectById(int deptid) {
		DeptDTO dept = sqlSession.selectOne(nameSpace + "selectById", deptid);
		return dept;
	}

	public int insert(DeptDTO dept) {
		result = sqlSession.insert(nameSpace + "insert", dept);
		return result;
	}

	public int update(DeptDTO dept) {
		result = sqlSession.update(nameSpace + "update", dept);
		return result;
	}

	public int delete(int deptid) {
		result = sqlSession.delete(nameSpace + "delete", deptid);
		return result;
	}

	public int deleteArray(Integer[] deptid) {
		return 0;
	}
}
