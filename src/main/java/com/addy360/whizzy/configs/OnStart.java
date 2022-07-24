package com.addy360.whizzy.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class OnStart {

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            log.info("Running scripts on startup");
        };
    }
}
