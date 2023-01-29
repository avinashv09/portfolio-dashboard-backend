package com.financebros.portfoliodashboardbackend.pricegetter;

import com.financebros.portfoliodashboardbackend.pricegetter.scrapper.PriceScrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {
    private final PriceScrapper priceScrapper;
    @GetMapping
    public void test() {
        priceScrapper.getPrices(null, null);
    }
}
