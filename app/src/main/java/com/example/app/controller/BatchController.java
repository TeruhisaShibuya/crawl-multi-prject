package com.example.app.controller;

import com.example.core.domain.Customer;
import com.example.core.repository.CustomerRepository;
import com.example.core.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/batch")
@RequiredArgsConstructor
public class BatchController {

    private final CustomerService customerService;

    /**
     * バッチで会員情報を全て取得する
     *
     * http://localhost:8080/batch/customer/list
     *
     * @return レスポンスエンティティ (会員リスト型)
     */
    @GetMapping("/customer/list")
    public ResponseEntity<List<Customer>> findAllCustomer() {

        System.out.println("BatchController処理開始");

        List<Customer> customers = customerService.findAll();

        if (customers != null) {
            for (Customer c: customers) {
                System.out.println("レコードID: " + c.getId());
                System.out.println("名前: " + c.getName());
            }
        } else {
            System.out.println("BatchController処理内部にて null : 空です");
        }

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
