package com.shinhan.myapp.emp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("empMybatis")
public class EmpDAOMybatis implements EmpDAOInterface {

	@Autowired
	SqlSession sqlSession;

	String nameSpace = "com.shinhan.emp.";
	int result;

	public List<EmpDTO> selectByArray(List<Integer> deptAry) {
		List<EmpDTO> empList = sqlSession.selectList(nameSpace + "selectByArray", deptAry);
		return empList;
	}
	
	public Map<String, Object> selectJoin2(String jobid) {
		Map<String, Object> empList = sqlSession.selectMap(nameSpace + "selectJoin2", jobid);
		return empList;
	}

	public List<EmpJoinDTO> selectJoin(String jobid) {
		List<EmpJoinDTO> empList = sqlSession.selectList(nameSpace + "selectJoin", jobid);
		return empList;
	}

	public List<JobDTO> selectAllJob() {
		List<JobDTO> jobList = sqlSession.selectList(nameSpace + "selectAllJob");
		return jobList;
	}

	public List<EmpDTO> selectByDept(int deptid) {
		List<EmpDTO> empList = sqlSession.selectList(nameSpace + "selectByDept", deptid);
		return empList;
	}

	public List<EmpDTO> selectByJob(String jobid) {
		List<EmpDTO> empList = sqlSession.selectList(nameSpace + "selectByJob", jobid);
		return empList;
	}

	public List<EmpDTO> selectBySalary(double salary) {
		List<EmpDTO> empList = sqlSession.selectList(nameSpace + "selectBySalary", salary);
		return empList;
	}

	public List<EmpDTO> selectByCondition(Map<String, Object> map) {
		List<EmpDTO> empList = sqlSession.selectList(nameSpace + "selectByCondition", map);
		return empList;
	}

	public List<EmpDTO> selectAll() {
		List<EmpDTO> empList = sqlSession.selectList(nameSpace + "selectAll");
		return empList;
	}

	public EmpDTO selectById(int empid) {
		EmpDTO emp = sqlSession.selectOne(nameSpace + "selectById", empid);
		return emp;
	}

	public int insert(EmpDTO emp) {
		result = sqlSession.insert(nameSpace + "insert", emp);
		return result;
	}

	public int update(EmpDTO emp) {
		result = sqlSession.update(nameSpace + "update", emp);
		return result;
	}

	public int delete(int empid) {
		result = sqlSession.delete(nameSpace + "delete", empid);
		return result;
	}

}
