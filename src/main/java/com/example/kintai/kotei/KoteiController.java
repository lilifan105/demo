package com.example.kintai.kotei;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    	List<KoteiInfo> resultList = jdbcTemplate.query(
                "SELECT * FROM kotei"
        		, new KoteiInfoMapper()
            );

		model.addAttribute("koteiList", resultList);

		return "kintai/kotei/list";
	}

    //工程作成画面を呼び出し
    @RequestMapping(value="/kintai/kotei/create",method = RequestMethod.GET)
    public String createKotei(Model model,KoteiForm form) {

    	return "kintai/kotei/create";
    }

    //工程作成を実行する
    @RequestMapping(value="/kintai/kotei/create",method = RequestMethod.POST)
    public String createKoteiExcute(Model model,@Validated KoteiForm form,BindingResult error) {

    	// 入力検証エラーなら元のフォームに戻る
		if(error.hasErrors()){
            return "kintai/kotei/create";
        }

		// 関連チェック：同じコードの工程をチェック
		try {
	    	jdbcTemplate.queryForObject(
	                "SELECT * FROM kotei WHERE koteicode = ?"
	        		, new KoteiInfoMapper()
	        		, new Object[] { form.getCallerKoteicode() }
	            );
			error.reject("validation.custom.exists-same-data");
            return "kintai/kotei/create";
		}
		catch(EmptyResultDataAccessException ex){
			// NOP
		}

		// DBへの追加処理
    	jdbcTemplate.update(
    			"INSERT into KOTEI(koteicode,koteimeisyo,koteiteigi) VALUES(?, ?, ?)"
				, form.getCallerKoteicode(), form.getCallerKoteimeisyo(),form.getCallerKoteiteigi());

    	return "redirect:/kintai/kotei/list";
    }

    //工程修正画面を呼び出す
    @RequestMapping(value="/kintai/kotei/updateordelete", params="command=変更" )
    public String updateKotei(Model model,String koteicode) {

    	// 更新対象データの取得
    	var result = jdbcTemplate.queryForObject(
                "SELECT * FROM kotei WHERE koteicode = ?"
        		, new KoteiInfoMapper()
        		, new Object[] { koteicode }
            );

    	// フォームの作成
    	var form = new KoteiForm();
    	form.setCallerKoteicode(result.getKoteicode());
    	form.setCallerKoteimeisyo(result.getKoteimeisyo());
    	form.setCallerKoteiteigi(result.getKoteiteigi());

    	model.addAttribute("koteiForm", form);
    	return "kintai/kotei/modify";
    }

    //工程修正を実行する
    @RequestMapping(value="/kintai/kotei/modify", method = RequestMethod.POST)
    public String modifyExcute(Model model,@Validated KoteiForm form,BindingResult error) {

    	// 入力検証エラーなら元のフォームに戻る
		if(error.hasErrors()){
            return "kintai/kotei/modify";
        }

		// DBへの追加処理
    	jdbcTemplate.update(
    			"update kotei set koteimeisyo = ?, koteiteigi = ? where koteicode = ?"
				,form.getCallerKoteimeisyo(),form.getCallerKoteiteigi(), form.getCallerKoteicode());

    	return "redirect:/kintai/kotei/list";
    }

    //工程削除画面を呼び出す
    @RequestMapping(value="/kintai/kotei/updateordelete", params="command=削除")
    public String deleteKotei(Model model,String koteicode) {

    	// 削除対象データの取得
    	KoteiInfo result = jdbcTemplate.queryForObject(
                "SELECT * FROM kotei WHERE koteicode = ?"
        		, new KoteiInfoMapper()
        		, new Object[] { koteicode }
            );

    	model.addAttribute("kotei", result);

    	return "kintai/kotei/delete";
    }

    //工程削除を実行する
    @RequestMapping(value="/kintai/kotei/delete", method = RequestMethod.POST)
    public String deleteExcute(Model model,String koteicode) {

    	//DBへの削除処理実行
		jdbcTemplate.update("delete from kotei where koteicode = ?", koteicode);

    	return "redirect:/kintai/kotei/list";
    }

  //工程削除をキャンセルする
    @RequestMapping(value="/kintai/kotei/delete", params="command=キャンセル")
    public String deleteCancel() {

    	return "redirect:/kintai/kotei/list";
    }
}
