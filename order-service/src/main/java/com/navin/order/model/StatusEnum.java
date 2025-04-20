package com.navin.order.model;

public enum StatusEnum {
	
	PLACED("Placed"),
	COMPLETED("Completed"),
	FAILED("Failed");
	
	private String value;

	StatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}

}
