package com.example.kintai.nitizisagyozisseki;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class NitizisagyozissekiMapper implements RowMapper<Nitizisagyozisseki>{
	public Nitizisagyozisseki mapRow(ResultSet rs, int rowNum) throws SQLException {
		var Zisseki = new Nitizisagyozisseki();
		Zisseki.setId(rs.getInt("id"));
		Zisseki.setSyainbango(rs.getString("syainbango"));
		Zisseki.setSagyobi(rs.getDate("sagyobi"));

		return Zisseki;
	}
}
