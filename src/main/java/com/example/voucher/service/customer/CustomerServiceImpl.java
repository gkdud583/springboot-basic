package com.example.voucher.service.customer;

import com.example.voucher.domain.customer.Customer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Override
	public List<Customer> findAll() {
		return new ArrayList<>();
	}

	@Override
	public Customer save(String name) {
		return null;
	}
}
