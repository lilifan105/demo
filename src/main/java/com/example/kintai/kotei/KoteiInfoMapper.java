package com.example.kintai.kotei;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class KoteiInfoMapper implements RowMapper<KoteiInfo>{
	public KoteiInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		var info = new KoteiInfo();
		info.setKoteicode(rs.getString("koteicode"));
		info.setKoteimeisyo(rs.getString("koteimeisyo"));
		info.setKoteiteigi(rs.getString("koteiteigi"));

		return info;
	}
}
