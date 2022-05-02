package com.example.voucher.domain.customer;

import java.time.LocalDateTime;

public class Customer {
	private Long voucherId;
	private String name;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Customer(Long voucherId, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.voucherId = voucherId;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getVoucherId() {
		return voucherId;
	}

	public String getName() {
		return name;
	}
}
