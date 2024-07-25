package com.example.core.service;

import com.example.core.domain.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> findAll();

    public void entry(Long id);
}
