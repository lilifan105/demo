package com.example.demo.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class HelloForm {

	private int callerId;
	
	@NotBlank
	@Size(min=2, max=30)
	private String callerName;
	
	@NotBlank
	private String callerCondition;

	public int getCallerId() {
		return callerId;
	}

	public void setCallerId(int callerId) {
		this.callerId = callerId;
	}

	public String getCallerCondition() {
		return callerCondition;
	}

	public void setCallerCondition(String callerCondition) {
		this.callerCondition = callerCondition;
	}

	public String getCallerName() {
		return callerName;
	}

	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}
}
