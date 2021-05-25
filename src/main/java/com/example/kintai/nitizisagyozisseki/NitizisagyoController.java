package com.example.kintai.nitizisagyozisseki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NitizisagyoController {

	@Autowired
    JdbcTemplate jdbcTemplate;


	//日次作業実績画面を表示
	@GetMapping("/kintai/nitizisagyozisseki/list")
	public String koteiList(Model model) {

		return "kintai/nitizisagyozisseki/list";
	}


    //日次作業実績登録画面を呼び出し
    @RequestMapping(value="kintai/nitizisagyozisseki/create",method = RequestMethod.GET)
    public String createKotei(Model model) {

    	return "kintai/nitizisagyozisseki/create";
    }

    //日次作業実績登録を実行する
    @RequestMapping(value="/kintai/nitizisagyozisseki/create",method = RequestMethod.POST)
    public String createKoteiExcute(Model model,@Validated ZissekiMeisaiForm form,BindingResult error) {

    	return "redirect:/nitizisagyozisseki/kotei/list";
    }

    //日次作業実績変更画面を呼び出す
    @RequestMapping(value="/kintai/nitizisagyozisseki/updateordelete", params="command=変更" )
    public String updateKotei(Model model,int nitizisagyozissekiid) {

    	return "kintai/nitizisagyozisseki/modify";
    }

    //日次作業実績変更を実行する
    @RequestMapping(value="/kintai/nitizisagyozisseki/modify", method = RequestMethod.POST)
    public String modifyExcute(Model model,@Validated ZissekiMeisaiForm form,BindingResult error) {

    	return "redirect:/kintai/nitizisagyozisseki/list";
    }

    //日次作業実績削除画面を呼び出す
    @RequestMapping(value="/kintai/nitizisagyozisseki/updateordelete", params="command=削除")
    public String deleteKotei(Model model,String nitizisagyozissekiid) {

    	return "kintai/nitizisagyozisseki/delete";
    }

    //日次作業実績削除を実行する
    @RequestMapping(value="/kintai/nitizisagyozisseki/delete", method = RequestMethod.POST)
    public String deleteExcute(Model model,String nitizisagyozissekiid) {

    	return "redirect:/kintai/nitizisagyozisseki/list";
    }

  //日次作業実績削除をキャンセルする
    @RequestMapping(value="/kintai/nitizisagyozisseki/delete", params="command=キャンセル")
    public String deleteCancel() {

    	return "redirect:/kintai/nitizisagyozisseki/list";
    }
}
