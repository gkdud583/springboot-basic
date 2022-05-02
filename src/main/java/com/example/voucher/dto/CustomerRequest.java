package com.example.voucher.dto;

import javax.validation.constraints.NotNull;

public class CustomerRequest {
	@NotNull(message = "이름이 null이 될 수 없습니다.")
	private String name;

	public CustomerRequest() {
	}

	public CustomerRequest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
