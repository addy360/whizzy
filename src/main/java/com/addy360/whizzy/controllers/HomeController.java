package com.addy360.whizzy.controllers;

import com.addy360.whizzy.helpers.Whizzy;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

    @GetMapping
    public String index() {

        return "index";
    }
}
