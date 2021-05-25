package com.example.kintai.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.kintai.syain.Syain;
import com.example.kintai.syain.SyainMapper;

@Component
public class UserUtil {

	@Autowired
	JdbcTemplate jdbcTemplate;
    
    public static String GetLoginUserName() {
	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName();//get logged in username
	      
	      return name;
	}
	
	public Syain GetLoginSyain() {
	    
		try {
			var result = jdbcTemplate.queryForObject(
	                "SELECT * FROM syain "
	    			+ "INNER JOIN bumon ON syain.syozokubumoncode = bumon.bumoncode "
	    			+ "INNER JOIN user_account ON syain.useraccountid = user_account.id "
	    			+ " WHERE user_account.name = ?"
	        		, new SyainMapper()
	        		, new Object[] { GetLoginUserName() }
	            );
			return result;
		}
		catch(EmptyResultDataAccessException ex){
			return null;
		}
	}
}
