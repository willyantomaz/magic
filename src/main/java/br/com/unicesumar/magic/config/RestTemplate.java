package br.com.unicesumar.magic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestTemplate {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
