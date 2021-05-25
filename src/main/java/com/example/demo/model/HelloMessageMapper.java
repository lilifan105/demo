package com.example.demo.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class HelloMessageMapper implements RowMapper<HelloMessage> {
	public HelloMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
		var msg = new HelloMessage();
		msg.setId(rs.getInt("id"));
		msg.setName(rs.getString("name"));
		msg.setCondition(rs.getString("condition"));
		
		return msg;
	}
}
