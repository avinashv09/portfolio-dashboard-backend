package com.financebros.portfoliodashboardbackend.pricegetter;

import com.financebros.portfoliodashboardbackend.pricegetter.service.PriceGetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {
    private final PriceGetterService priceGetterService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void test() {
        priceGetterService.updatePrices();
    }
}
