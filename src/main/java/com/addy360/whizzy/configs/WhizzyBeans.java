package com.addy360.whizzy.configs;

import com.addy360.whizzy.helpers.WhizzyPrints;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class WhizzyBeans {

    @Bean
    ExecutorService executorService() {
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Bean
    WhizzyPrints whizzyPrints() {
        return new WhizzyPrints(executorService());
    }

}
