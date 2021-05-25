package com.example.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService implements UserDetailsService {
    
	@Autowired
    JdbcTemplate jdbcTemplate;
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity ue = null;
        
		try {
			ue = jdbcTemplate.queryForObject(
	                "SELECT * FROM USER_ACCOUNT WHERE name = ?"
	        		, new UserEntityMapper()
	        		, new Object[] { username }
	            );
		}
		catch(EmptyResultDataAccessException ex){
			throw new UsernameNotFoundException("user " + username + " not found." );
		}
        
		return ue;
	}

}
