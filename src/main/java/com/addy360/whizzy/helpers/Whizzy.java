package com.addy360.whizzy.helpers;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;


@Slf4j
public class Whizzy {
    public void getPage() {

        try {
            Document document = Jsoup.connect("https://www.whizztanzania.com/tenders").get();
        } catch (Exception e) {
            log.info("Error while connecting to webpage : {}", e.getMessage());
        }
    }
}
