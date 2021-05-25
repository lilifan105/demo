package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.HelloMessage;
import com.example.demo.model.HelloMessageMapper;

@Controller
public class MemberController {
	
    @Autowired
    JdbcTemplate jdbcTemplate;

	// メンバーリスト表示
    @RequestMapping("/")
	public String defaultRoute(Model model, HelloForm form) {

    	// 単にリダイレクトする
		return "redirect:/showmembers"; 
	}
    
    // メンバーリスト表示
    @GetMapping("/showmembers")
	public String getMemberList(Model model, HelloForm form) {

    	List<HelloMessage> resultList = jdbcTemplate.query(
                "SELECT * FROM message"
        		, new HelloMessageMapper()
            );
        
		model.addAttribute("memberList", resultList);

		return "demo/member/memberlist"; 
	}

}
