package com.shinhan.myapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.vo.DeptDTO;

import net.firstzone.util.DBUtil;

@Repository
public class DeptDAO implements DeptDAOInterface{
	@Autowired
	@Qualifier("dataSource")
	DataSource ds;

	String sql_selectAll = "select * from departments order by 1";
	String sql_selectById = "select * from departments where department_id = ?";
	String sql_insert = "insert into departments values(?,?,?,?) ";
	String sql_update = "update departments set department_name=?, manager_id=?, location_id=? where department_id=?";
	String sql_delete = "delete from departments where department_id = ?";

	Connection conn;
	PreparedStatement st;
	ResultSet rs;
	int result;

	public List<DeptDTO> selectAll() {
		List<DeptDTO> deptlist = new ArrayList<>();
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql_selectAll);
			rs = st.executeQuery();
			while (rs.next()) {
				DeptDTO dept = new DeptDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				deptlist.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}

		return deptlist;
	}

	// 2.?ÉÅ?Ñ∏Î≥¥Í∏∞
	public DeptDTO selectById(int deptid) {
		DeptDTO dept = null;
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql_selectById);
			st.setInt(1, deptid);
			rs = st.executeQuery();
			while (rs.next()) {
				dept = new DeptDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return dept;

	}

	// 3.?ûÖ?†•
	public int insert(DeptDTO dept) {
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql_insert);
			st.setInt(1, dept.getDepartment_id());
			st.setString(2, dept.getDepartment_name());
			st.setInt(3, dept.getManager_id());
			st.setInt(4, dept.getLocation_id());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return result;
	}

	// 4.?àò?†ï
	public int update(DeptDTO dept) {
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql_update);
			st.setInt(4, dept.getDepartment_id());
			st.setString(1, dept.getDepartment_name());
			st.setInt(2, dept.getManager_id());
			st.setInt(3, dept.getLocation_id());
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return result;
	}

	// 5.?Ç≠?†ú
	public int delete(int deptid) {
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			st = conn.prepareStatement(sql_delete);
			st.setInt(1, deptid);
			result = st.executeUpdate();

			conn.commit(); // DB?óê Î∞òÏòÅ?ïú?ã§.

		} catch (SQLException e) {
			try {
				conn.rollback(); // DB?óê ?ûë?óÖ?ïú ?Ç¥?ö©Ï∑®ÏÜå
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return result;
	}
}
