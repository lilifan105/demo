package com.example.auth;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserEntityMapper  implements RowMapper<UserEntity> {
	public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		var ue = new UserEntity();
		ue.setId(rs.getLong("id"));
		ue.setUsername(rs.getString("name"));
		ue.setPassword(rs.getString("password"));
		
		return ue;
	}
}