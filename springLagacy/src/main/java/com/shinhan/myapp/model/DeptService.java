package com.shinhan.myapp.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shinhan.myapp.vo.DeptDTO;

@Service
public class DeptService {

	@Autowired
	@Qualifier("deptMybatis")
	DeptDAOInterface deptDao;

	public List<DeptDTO> selectAllService() {
		return deptDao.selectAll();
	}

	public DeptDTO selectByIdService(int deptid) {
		return deptDao.selectById(deptid);
	}

	public int insertService(DeptDTO dept) {
		return deptDao.insert(dept);
	}

	public int updateService(DeptDTO dept) {
		return deptDao.update(dept);
	}

	public int deleteService(int deptid) {
		return deptDao.delete(deptid);
	}
}
