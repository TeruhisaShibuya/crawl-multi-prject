package com.example.app.controller;

import com.example.core.domain.Customer;
import com.example.core.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    /**
     * 会員のリスト画面
     * http://localhost:8080/customer/list
     *
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String showList(Model model){

        List<Customer> customer = customerService.findAll();
        model.addAttribute("customers", customer);

        return "customer/list";
    }


    /**
     * 会員のリスト画面
     * http://localhost:8080/customer/entry
     *
     * @param model
     * @return
     */
    @GetMapping("/entry")
    public String showTest(Model model){

        customerService.entry(1L);

        return "customer/test";
    }
}
