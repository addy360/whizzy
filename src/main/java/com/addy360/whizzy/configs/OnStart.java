package com.addy360.whizzy.configs;

import com.addy360.whizzy.helpers.WhizzyPrints;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
@RequiredArgsConstructor
public class OnStart {

    private final WhizzyPrints whizzyPrints;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            whizzyPrints.fetchAndPrint();
        };
    }
}
