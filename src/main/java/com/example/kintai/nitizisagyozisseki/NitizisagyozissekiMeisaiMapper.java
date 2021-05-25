package com.example.kintai.nitizisagyozisseki;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class NitizisagyozissekiMeisaiMapper implements RowMapper<NitizisagyozissekiMeisai>{
	public NitizisagyozissekiMeisai mapRow(ResultSet rs, int rowNum) throws SQLException {
		var Meisai = new NitizisagyozissekiMeisai();
		Meisai.setNitizisagyozissekiid(rs.getString("nitizisagyozissekiid"));
		Meisai.setMeisaibango(rs.getInt("meisaibango"));
		Meisai.setProjectcode(rs.getString("projectcode"));
		Meisai.setSagyozikan(rs.getString("sagyozikan"));
		Meisai.setTokkiziko(rs.getString("tokkiziko"));
		Meisai.setSagyokoteicode(rs.getString("sagyokoteicode"));

		return Meisai;
	}
}
