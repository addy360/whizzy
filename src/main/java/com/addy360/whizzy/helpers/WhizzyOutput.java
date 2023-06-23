package com.addy360.whizzy.helpers;

import com.addy360.whizzy.dtos.WhizzyItemDetails;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Slf4j
public abstract class WhizzyOutput {

    ExecutorService service;

    public WhizzyOutput(ExecutorService service) {
        this.service = service;
    }

    public void init() {
        dataProcessor();
    }

    private void dataProcessor() {
        Jobs jobs = new Jobs();
        Tenders tenders = new Tenders();

        List<WhizzyPage> pages = Arrays.asList(jobs, tenders);

        pages.parallelStream()
                .map(whizzyPage -> service.submit(() -> whizzyPage
                        .getPageItems()
                        .parallelStream()
                        .map(whizzyItem -> whizzyPage instanceof Jobs
                                ? service.submit(() -> jobs.getDetails(whizzyItem))
                                : service.submit(() -> tenders.getDetails(whizzyItem)))
                        .collect(Collectors.toList())))
                .forEach(listFuture -> service.submit(() -> {

                    service.submit(() -> {
                        prepareItemDetailPages(listFuture);
                    });


                }));

    }

    private void prepareItemDetailPages(Future<List<Future<WhizzyItemDetails>>> listFuture) {
        try {
            listFuture.get()
                    .parallelStream()
                    .forEach(whizzyItemDetailsFuture -> {
                        service.submit(() -> {
                            extractedData(whizzyItemDetailsFuture);
                        });
                    });
        } catch (InterruptedException | ExecutionException e) {
            log.info("Error while fetching data : {}", e.getMessage());
        }
    }

    private void extractedData(Future<WhizzyItemDetails> whizzyItemDetailsFuture) {
        try {
            processData(whizzyItemDetailsFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            log.info("Error while fetching data : {}", e.getMessage());
        }
    }

    abstract void processData(WhizzyItemDetails whizzyItemDetails);
}
