package com.example.kintai.syain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SyainMapper implements RowMapper<Syain> {
	public Syain mapRow(ResultSet rs, int rowNum) throws SQLException {
		var msg = new Syain();
		msg.setSyainBango(rs.getString("syainbango"));
		msg.setSei(rs.getString("sei"));
		msg.setMei(rs.getString("mei"));
		msg.setNyusyaNengappi(rs.getDate("nyusyanengappi"));
		msg.setSyozokuBumonCode(rs.getString("syozokubumoncode"));
		msg.setSyozokuBumonMesyo(rs.getString("bumonmeisyo"));
		msg.setSyozokuBumonRyakusyo(rs.getString("bumonryakusyo"));
		msg.setUserAccountId(rs.getInt("useraccountid"));
		
		return msg;
	}
}
