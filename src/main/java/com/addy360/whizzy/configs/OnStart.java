package com.addy360.whizzy.configs;

import com.addy360.whizzy.dtos.WhizzyItem;
import com.addy360.whizzy.helpers.Jobs;
import com.addy360.whizzy.helpers.Tenders;
import com.addy360.whizzy.helpers.WhizzyPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class OnStart {

    private final ExecutorService service;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            log.info("Running scripts on startup");

            Jobs jobs = new Jobs();
            Tenders tenders = new Tenders();

            List<WhizzyItem> items = jobs.getPageItems();
            items.parallelStream().forEach(whizzyItem -> service.submit(() -> log.info("Jobs details : {}", jobs.getDetails(whizzyItem))));


            List<WhizzyItem> pageItems = tenders.getPageItems();
            pageItems.parallelStream().forEach(whizzyItem -> service.execute(() -> log.info("Tenders : {}", tenders.getDetails(whizzyItem))));

        };
    }
}
