package com.adesso.eaydemir.eth.tdp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories
@EnableFeignClients(basePackages = "com.adesso.eaydemir.eth.tdp.integration.moralis")
@Configuration
public class TransactionDataProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionDataProviderApplication.class, args);
    }

}
