package com.example.kintai.kotei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class KoteiController {

	@Autowired
    JdbcTemplate jdbcTemplate;

	// 工程リスト表示
    @GetMapping("/kintai/kotei/list")
	public String koteiList(Model model) {

    	// 単にリダイレクトする
		return "kintai/kotei/list";
	}

    //工程作成画面を呼び出し
    @RequestMapping(value="/createkotei",method = RequestMethod.GET)
    public String createKotei(Model model,KoteiForm form) {

    	return "kintai/kotei/create";
    }

    //工程作成を実行する
    @RequestMapping(value="/createkotei",method = RequestMethod.POST)
    public String createKoteiExcute(Model model,KoteiForm form) {

    	return "kintai/kotei/list";
    }

    //工程修正画面を呼び出す
    @RequestMapping(value="/kintai/kotei/updateordelete", params="command=変更" )
    public String updateKotei() {
    	return "kintai/kotei/modify";
    }

    //工程修正を実行する
    @RequestMapping(value="/modifykotei", method = RequestMethod.POST)
    public String modifyExcute() {
    	return "kintai/kotei/list";
    }

    //工程削除画面を呼び出す
    @RequestMapping(value="/kintai/kotei/updateordelete", params="command=削除")
    public String deleteKotei() {
    	return "kintai/kotei/delete";
    }

    //工程削除を実行する
    public String deleteExcute() {
    	return "kintai/kotei/list";
    }
}
