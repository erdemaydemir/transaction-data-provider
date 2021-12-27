package com.adesso.eaydemir.eth.tdp.integration.moralis.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

public class MoralisProviderClientConfiguration {

    @Value("${feign.client.config.moralis.apiKey}")
    private String apiKey;

    @Bean
    @ConditionalOnProperty(name = "feign.client.config.moralis.apiKey")
    public RequestInterceptor basicAuthRequestInterceptor() {
        return new MoralisAuthRequestInterceptor();
    }

    public class MoralisAuthRequestInterceptor implements RequestInterceptor {
        @Override
        public void apply(RequestTemplate template) {
            template.header("X-API-Key", apiKey);
        }
    }
}
