package com.example.kintai.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
    @Autowired
    JdbcTemplate jdbcTemplate;
   
    //------------------------------------------------------- 共通画面表示機能
    
    // トップメニュー
    @GetMapping("/kintai")
	public String mainMenu(Model model) {

		return "kintai/common/menu"; 
	}


    
}
