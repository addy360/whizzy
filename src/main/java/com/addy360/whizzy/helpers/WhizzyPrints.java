package com.addy360.whizzy.helpers;

import com.addy360.whizzy.dtos.WhizzyItemDetails;
import lombok.extern.slf4j.Slf4j;


import java.util.concurrent.ExecutorService;

@Slf4j
public class WhizzyPrints extends WhizzyOutput {

    public WhizzyPrints(ExecutorService service) {
        super(service);
    }

    @Override
    void processData(WhizzyItemDetails whizzyItemDetails) {
        log.info("WhizzyItemDetails : {}", whizzyItemDetails);
    }
}
