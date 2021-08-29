package org.prgrms.orderapp;

import org.prgrms.orderapp.model.Customer;
import org.prgrms.orderapp.repository.CsvCustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import java.util.UUID;

public class OrderTester {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(OrderAppApplication.class, args);

        var customer1 = new Customer(UUID.randomUUID(), "John Doe", "Seoul, South Korea", 25);
        var customer2 = new Customer(UUID.randomUUID(), "Kevin Park", "SanFrancisco, United States", 36);
        var customer3 = new Customer(UUID.randomUUID(), "Allen Song", "Tokyo, Japan", 50);
        var csvCustomerRepository = applicationContext.getBean(CsvCustomerRepository.class);
        var c1 = csvCustomerRepository.createFromString(customer1.toString());
        Assert.isTrue(customer1.equals(c1.get()), "c1 and customer1 is not equal");

        csvCustomerRepository.getBlacklist().forEach(System.out::println);
    }
}