package com.example.voucher.controller;

import com.example.voucher.domain.voucher.VoucherType;
import com.example.voucher.dto.VoucherResponse;
import com.example.voucher.service.voucher.VoucherService;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.example.voucher.exception.ErrorMessage.INVALID_INPUT;


@Controller
public class VoucherController {
	private final VoucherService voucherService;

	public VoucherController(VoucherService voucherService) {
		this.voucherService = voucherService;
	}

	public void save(VoucherType voucherType, int discountAmount) {
		if (voucherType == null) {
			// TODO: 로그 남기기
			throw new IllegalArgumentException(INVALID_INPUT.name());
		}
		voucherService.save(voucherType, discountAmount);
	}

	public List<VoucherResponse> findAll() {
		return null;
	}
}
