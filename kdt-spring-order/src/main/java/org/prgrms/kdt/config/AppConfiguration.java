package org.prgrms.kdt.config;

import org.prgrms.kdt.order.Order;
import org.prgrms.kdt.order.OrderRepository;
import org.prgrms.kdt.order.OrderService;
import org.prgrms.kdt.voucher.repository.MemoryVoucherRepository;
import org.prgrms.kdt.voucher.repository.VoucherRepository;
import org.prgrms.kdt.voucher.service.VoucherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 서비스 간 의존관계 및 객체 생성 해줌

@Configuration
public class AppConfiguration {

    @Bean
    public VoucherRepository voucherRepository(){
        return new MemoryVoucherRepository();
    }

    @Bean
    public OrderRepository orderRepository() {
        return new OrderRepository() {
            @Override
            public void insert(Order order) {
            }
        };
    }

    @Bean
    public VoucherService voucherService(VoucherRepository voucherRepository) {
        return new VoucherService(voucherRepository);
    }

    @Bean
    public OrderService orderService(VoucherService voucherService, OrderRepository orderRepository) {
        return new OrderService(voucherService, orderRepository);
    }
}
