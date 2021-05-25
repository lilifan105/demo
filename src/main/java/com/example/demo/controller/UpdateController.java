package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.HelloMessage;
import com.example.demo.model.HelloMessageMapper;

@Controller
public class UpdateController {
	
    @Autowired
    JdbcTemplate jdbcTemplate;

	// 新規作成画面呼び出し
    @RequestMapping(value="/createmember", method = RequestMethod.GET)
	public String createMember(Model model, HelloForm form) {
    	
		return "demo/member/create"; 
	}
    
    // 新規作成処理実行
    @RequestMapping(value="/createmember", method = RequestMethod.POST)
	public String createMemberExecute(Model model, @Validated HelloForm form, BindingResult error) {

        // 入力検証エラーなら元のフォームに戻る
		if(error.hasErrors()){
            return "demo/member/create";
        }
		
		// 関連チェック：同姓同名のチェック
		try {
	    	jdbcTemplate.queryForObject(
	                "SELECT * FROM message WHERE name = ?"
	        		, new HelloMessageMapper()
	        		, new Object[] { form.getCallerName() }
	            );
			error.reject("validation.custom.exists-same-name");
            return "demo/member/create";
		}
		catch(EmptyResultDataAccessException ex){
			// NOP
		}
		
		// DBへの追加処理
    	jdbcTemplate.update(
    			"INSERT into MESSAGE(name, condition) VALUES(?, ?)"
				, form.getCallerName(), form.getCallerCondition());
    	
		return "redirect:/showmembers"; 
	}
    
    // 更新画面呼び出し
    @RequestMapping(value="/updatemember", params="command=Modify")
	public String updateMemberList(Model model, int id) {

    	// 更新対象データの取得
    	var result = jdbcTemplate.queryForObject(
                "SELECT * FROM message WHERE id = ?"
        		, new HelloMessageMapper()
        		, new Object[] { id }
            );
    	
    	// フォームの作成
    	var form = new HelloForm();
    	form.setCallerId(result.getId());        
    	form.setCallerName(result.getName());        
    	form.setCallerCondition(result.getCondition());        

    	model.addAttribute("helloForm", form);

		return "demo/member/modify"; 
	}
    
	// 更新処理実行
    @RequestMapping(value="/modifymember", method = RequestMethod.POST)
	public String modifyMember(Model model, @Validated HelloForm form, BindingResult error) {

		// 入力検証エラーなら元のフォームに戻る
		if(error.hasErrors()){
            return "demo/member/modify";
        }
		
		// 関連チェック:ボブの条件チェック
		if(form.getCallerName().startsWith("Bob") 
				&& form.getCallerCondition().toLowerCase().contains("bad")){
			error.reject("validation.custom.bob-should-not-be-bad");
            return "demo/member/modify";
        }
        
    	// DBへの更新処理
		jdbcTemplate.update("update message set name = ?, condition = ? where id = ?"
				, form.getCallerName(), form.getCallerCondition(), form.getCallerId());
        
		return "redirect:/showmembers"; 
	}

	// 削除画面呼び出し
    @RequestMapping(value="/updatemember", params="command=Delete")
	public String deleteMemberList(Model model, int id) {

    	// 削除対象データの取得
    	HelloMessage result = jdbcTemplate.queryForObject(
                "SELECT * FROM message WHERE id = ?"
        		, new HelloMessageMapper()
        		, new Object[] { id }
            );
    	
    	model.addAttribute("member", result);

		return "demo/member/delete"; 
	}

	// 削除処理実行
    @RequestMapping(value="/deleteconfirmed")
	public String deleteExecute(Model model, int id) {
    	
    	//DBへの削除処理実行
		jdbcTemplate.update("delete from message where id = ?", id);

		return "redirect:/showmembers"; 
	}
    
    // 削除処理キャンセル
    @RequestMapping(value="/deleteconfirmed", params="command=Cancel")
	public String cancelDelete(Model model, int id) {

    	// 単にリダイレクトする
		return "redirect:/showmembers"; 
	}
    
}
