package com.example.kintai.kotei;

import javax.validation.constraints.NotBlank;

public class KoteiForm {

	private int callerKoteicode;

	@NotBlank
	private String callerKoteimeisyo;

	@NotBlank
	private String callerKoteiteigi;

	public int getCallerKoteicode() {
		return callerKoteicode;
	}

	public void setCallerKoteicode(int callerKoteicode) {
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
