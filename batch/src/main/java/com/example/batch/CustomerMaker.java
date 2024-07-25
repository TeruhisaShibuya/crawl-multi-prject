package com.example.batch;

import com.example.core.domain.Customer;
import com.example.core.repository.CustomerRepository;
import com.example.core.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerMaker implements Runnable {

    private final CustomerService customerService;

    private final RestTemplate restTemplate;
    private final HttpServletRequest request;

    private final String targetPath = "/batch/customer/list";


    /**
     * 顧客のデータを定期的に取得するためのテスト
     */
    @Override
    public void run() {

        for (; ;){

            System.out.println("バッチ実行開始");

//            String appDomainUrl = getBaseUrl(request);
//            String targetUrl = appDomainUrl + targetPath;

            //String targetUrl = "http://localhost:8080/batch/customer/list";
            // 指定のURLにアクセスしてデータを取得 okHttp3みたいな形式で指定のURLにアクセスする
//            ResponseEntity<List<Customer>> response = restTemplate.exchange(
//                    targetUrl,
//                    HttpMethod.GET,
//                    null,
//                    new ParameterizedTypeReference<List<Customer>>() {}
//            );
//            System.out.println("コントローラへのアクセス処理終了");
//            List<Customer> customers = response.getBody();

            // CustomerRepositoryでテーブルからデータを取得する
            List<Customer> customers = customerService.findAll();

            // getBodyメソッドで指定の型のデータが取得できる
            if (!customers.isEmpty()) {
                for (Customer c: customers) {
                    System.out.println("レコードID: " + c.getId());
                    System.out.println("名前: " + c.getName());

                }
            } else {
                System.out.println("null : 空です");
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }


    private String getBaseUrl(HttpServletRequest request) {
        return ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();
    }
}
