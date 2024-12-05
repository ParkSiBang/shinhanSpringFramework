package com.shinhan.myapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.vo.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	@Qualifier("dataSource")
	DataSource ds;

	Connection conn;
	PreparedStatement st;
	ResultSet rs;
	String select_login = "select * from members where member_id=?";

	public MemberDTO login(String mid, String mpass) {
		MemberDTO member = null;
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(select_login);
			st.setString(1, mid);
			rs = st.executeQuery();

			if (rs.next()) {
				String getPass = rs.getString("member_pass");
				if (mpass.equals(getPass)) {
					member = new MemberDTO(mid, mpass, rs.getString("member_name"), rs.getString("member_email"));
				} else {
					member = MemberDTO.builder().member_id("-1").build();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return member;
	}
}
