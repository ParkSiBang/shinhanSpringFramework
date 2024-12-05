package com.shinhan.myapp.emp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EmpService {

	@Autowired
	@Qualifier("empMybatis")
	EmpDAOInterface empDAO;
	
	public List<EmpDTO> selectByArray(List<Integer> deptAry) {
		return empDAO.selectByArray(deptAry);
	}

	public List<EmpJoinDTO> selectByJobJoin(String job_id) {
		return empDAO.selectJoin(job_id);
	}
	
	public List<EmpDTO> selectByDept(int deptid) {
		return empDAO.selectByDept(deptid);
	}

	public List<EmpDTO> selectByJob(String job_id) {
		return empDAO.selectByJob(job_id);
	}

	public List<EmpDTO> selectBySalary(double salary) {
		return empDAO.selectBySalary(salary);
	}

	public List<EmpDTO> selectByCondition(Map<String, Object> map) {
		return empDAO.selectByCondition(map);
	}

	public List<EmpDTO> selectAllService() {
		return empDAO.selectAll();
	}

	public EmpDTO selectByIdService(int empid) {
		return empDAO.selectById(empid);
	}

	public int insertService(EmpDTO emp) {
		return empDAO.insert(emp);
	}

	public int updateService(EmpDTO emp) {
		return empDAO.update(emp);
	}

	public int deleteService(int empid) {
		return empDAO.delete(empid);
	}

	public List<JobDTO> selectAllJobService() {
		return empDAO.selectAllJob();
	}
}
