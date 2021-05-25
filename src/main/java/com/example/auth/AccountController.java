package com.example.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/auth")
public class AccountController {
	
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    //------------------------------------------------------- 一覧表示機能
    
    // 一覧表示
    @GetMapping("/accountlist")
	public String getAccountList(Model model) {

		var ue = jdbcTemplate.query(
                "SELECT * FROM USER_ACCOUNT ORDER BY name "
        		, new UserEntityMapper()
            );
        
		model.addAttribute("accountList", ue);

		return "auth/accountlist"; 
	}

    //------------------------------------------------------- 登録機能

    // 新規作成画面呼び出し
    @RequestMapping(value="/createaccount", method = RequestMethod.GET)
	public String createMember(Model model, AccountForm form) {
    	
		return "auth/createaccount"; 
	}
    
    // 新規作成処理実行
    @RequestMapping(value="/createaccount", method = RequestMethod.POST)
	public String createMemberExecute(Model model, @Validated AccountForm accountForm, BindingResult error) {

        // 入力検証エラーなら元のフォームに戻る
		if(error.hasErrors()){
            return "auth/createaccount";
        }
		
		// パスワードのハッシュ化用
        var encoder = new BCryptPasswordEncoder();

		// DBへの追加処理
    	jdbcTemplate.update(
    			"INSERT into USER_ACCOUNT(name, password) VALUES(?, ?)"
				, accountForm.getUsername()
				, encoder.encode(accountForm.getPassword())
				);
    	
		return "redirect:/auth/accountlist"; 
	}
    
    //------------------------------------------------------- 変更機能

    // 変更画面呼び出し
    @RequestMapping(value="/updateordelete", params="command=変更", method = RequestMethod.POST)
	public String modifyAccount(Model model, int id) {

    	// 変更対象データの取得
		var result = jdbcTemplate.queryForObject(
                "SELECT * FROM USER_ACCOUNT WHERE id = ? "
        		, new UserEntityMapper()
        		, id
            );
		
    	// フォームの作成
    	var form = new AccountForm();
    	form.setId(result.getId());        
    	form.setUsername(result.getUsername());        
    	model.addAttribute("accountForm", form);
    	
		return "auth/modifyaccount"; 
	}
    
    // 変更処理実行
    @RequestMapping(value="/modifyaccount", method = RequestMethod.POST)
	public String modifyKokyaku(Model model, @Validated AccountForm form, BindingResult error) {

		// 入力検証エラーなら元のフォームに戻る
		if(error.hasErrors()){
            return "auth/modifyaccount";
        }
		
		// パスワードのハッシュ化用
        var encoder = new BCryptPasswordEncoder();
		
    	// DBへの更新処理
		jdbcTemplate.update("UPDATE user_account SET name = ?, password = ? where id = ?"
				, form.getUsername(), encoder.encode(form.getPassword()), form.getId());
        
		return "redirect:/auth/accountlist"; 
	} 
    
}
