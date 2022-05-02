//package com.example.voucher.api;
//
//import com.example.voucher.VoucherApplication;
//import com.example.voucher.controller.api.CustomerRestController;
//import com.example.voucher.domain.customer.Customer;
//import com.example.voucher.domain.voucher.FixedAmountVoucher;
//import com.example.voucher.domain.voucher.Voucher;
//import com.example.voucher.domain.voucher.VoucherType;
//import com.example.voucher.dto.*;
//import com.example.voucher.service.customer.CustomerService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static com.example.voucher.domain.voucher.VoucherType.FIXED_AMOUNT_VOUCHER;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.mock;
//import static org.springframework.http.HttpStatus.BAD_REQUEST;
//import static org.springframework.http.HttpStatus.OK;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(controllers = CustomerRestController.class,
//		excludeAutoConfiguration = VoucherApplication.class)
//@DisplayName("CustomerRestController 클래스의")
//
//public class CustomerRestControllerTest {
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private VoucherApplication voucherApplication;
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@MockBean
//	private CustomerService customerService;
//
//	@Nested
//	@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//	class findAll메서드는 {
//
//		@Test
//		@DisplayName("전체 고객을 조회하고 반환한다")
//		void 전체_고객을_조회하고_반환한다() throws Exception {
//			List<Customer> customers = Arrays.asList(new Customer(1L, "testName", LocalDateTime.now(), null));
//			List<CustomerResponse> customerResponses = customers.stream()
//					.map((v) -> CustomerResponse.from(v))
//					.collect(Collectors.toList());
//			String responseJsonString = objectToJsonString(customerResponses);
//			given(customerService.findAll())
//					.willReturn(customers);
//
//			mockMvc.perform(get("/api/v1/customers"))
//					.andExpect(status().isOk())
//					.andExpect(content().json(responseJsonString));
//		}
//
//		@Nested
//		@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//		class 저장된_바우처가_없다면 {
//
//			@Test
//			@DisplayName("빈 리스트를 반환한다")
//			void 빈_리스트를_반환한다() throws Exception {
//
//				String responseJsonString = objectToJsonString(Collections.EMPTY_LIST);
//				given(customerService.findAll())
//						.willReturn(Collections.EMPTY_LIST);
//
//
//				mockMvc.perform(get("/api/v1/customers"))
//						.andExpect(status().isOk())
//						.andExpect(content().json(responseJsonString));
//			}
//		}
//	}
//
//	@Nested
//	@Order(1)
//	@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//	class save메서드는 {
//
//		@Test
//		@DisplayName("바우처를 저장하고 저장된 바우처를 반환한다")
//		void 바우처를_저장하고_저장된_바우처를_반환한다() throws Exception {
//
//			CustomerRequest customerRequest = new CustomerRequest("testName");
//			String requestJsonString = objectToJsonString(customerRequest);
//			Customer customer = new Customer(1L, customerRequest.getName(), LocalDateTime.now(), null);
//			CustomerResponse customerResponse = new CustomerResponse(customer.getVoucherId(), customer.getName());
//			String responseJsonString = objectToJsonString(customerResponse);
//			given(customerService.save(anyString()))
//					.willReturn(customer);
//
//			mockMvc.perform(post("/api/v1/customers")
//					.content(requestJsonString)
//					.contentType(APPLICATION_JSON))
//					.andExpect(status().isOk())
//					.andExpect(content().json(responseJsonString));
//		}
//
//		@Nested
//		@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//		class 이름이_null이라면 {
//
//			@Test
//			@DisplayName("예외 응답을 반환한다")
//			void 예외_응답을_반환한다() throws Exception {
//
//				CustomerRequest customerRequest = new CustomerRequest(null);
//				String requestJsonString = objectToJsonString(customerRequest);
//				Customer customer = new Customer(null, customerRequest.getName(), LocalDateTime.now(), null);
//				String responseJsonString = objectToJsonString(new CustomerResponse(customer.getVoucherId(), customer.getName()));
//
//				MvcResult mvcResult = mockMvc.perform(post("/api/v1/vouchers")
//								.content(requestJsonString)
//								.contentType(APPLICATION_JSON))
//						.andExpect(status().isBadRequest())
//						.andReturn();
//
//				assertThat(mvcResult.getResolvedException().getMessage()).contains("이름이 null이 될 수 없습니다");
//			}
//		}
//	}
//
//	private String objectToJsonString(Object obj) {
//		try {
//			return objectMapper.writeValueAsString(obj);
//		} catch (JsonProcessingException e) {
//			throw new RuntimeException(e.getMessage());
//		}
//	}
//}