package com.example.core.service;

import com.example.core.domain.Customer;
import com.example.core.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional
    public void entry(Long id) {
        Customer customer = new Customer();
        customer.setName("テスト名");
        customerRepository.save(customer);
    }
}
