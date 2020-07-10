package com.thlxgskccx.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

//@Service
@Configuration
public class HttpClient {
    /*public  String client(String url, HttpMethod method, MultiValueMap<String,String> params) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response1 = template.getForEntity(url, String.class);
        return response1.getBody();
    }*/
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(15000);
        factory.setReadTimeout(5000);
        return factory;
    }
}
