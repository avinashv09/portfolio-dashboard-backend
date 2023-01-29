package com.financebros.portfoliodashboardbackend.pricegetter;

import com.financebros.portfoliodashboardbackend.pricegetter.service.PriceGetterService;
import com.financebros.portfoliodashboardbackend.pricewatcher.dto.ScripResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.ParseException;

@RestController
@RequestMapping("/pricegetter")
@RequiredArgsConstructor
public class DemoController {
    private final PriceGetterService priceGetterService;
    @GetMapping("/triggerUpdate")
    @ResponseStatus(HttpStatus.OK)
    public void test() throws Exception {
        priceGetterService.updatePrices();
    }
}
