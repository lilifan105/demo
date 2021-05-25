package com.example.kintai.kotei;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class KoteiForm {

	@NotBlank
	@Size(min=4,max=4,message="工程コードに数字4桁を入力してください")
	private String callerKoteicode;

	@NotBlank
	@Size(max=100)
	private String callerKoteimeisyo;

	@Size(max=1024)
	private String callerKoteiteigi;

	public String getCallerKoteicode() {
		return callerKoteicode;
	}

	public void setCallerKoteicode(String callerKoteicode) {
		this.callerKoteicode = callerKoteicode;
	}

	public String getCallerKoteimeisyo() {
		return callerKoteimeisyo;
	}

	public void setCallerKoteimeisyo(String callerKoteimeisyo) {
		this.callerKoteimeisyo = callerKoteimeisyo;
	}

	public String getCallerKoteiteigi() {
		return callerKoteiteigi;
	}

	public void setCallerKoteiteigi(String callerKoteiteigi) {
		this.callerKoteiteigi = callerKoteiteigi;
	}


}
