package com.financebros.portfoliodashboardbackend.pricegetter.controller;

import com.financebros.portfoliodashboardbackend.dto.pricegetter.ScripPricesResponse;
import com.financebros.portfoliodashboardbackend.dto.pricewatcher.ScripIdentifierRequest;
import com.financebros.portfoliodashboardbackend.pricegetter.service.PriceGetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(PriceGetterController.PRICEGETTER_API_END_POINT)
public class PriceGetterController {
    public static final String PRICEGETTER_API_END_POINT = "/pricegetter";
    public static final String TRIGGER_UPDATE_END_POINT = "/triggerUpdate";
    public static final String GET_PRICES_END_POINT = "/get-prices";
    private final PriceGetterService priceGetterService;

    @GetMapping(TRIGGER_UPDATE_END_POINT)
    @ResponseStatus(HttpStatus.OK)
    public void test() throws Exception {
        priceGetterService.updatePrices();
    }

    @GetMapping(GET_PRICES_END_POINT)
    @ResponseStatus(HttpStatus.OK)
    public List<ScripPricesResponse> getPrices(@RequestBody ScripIdentifierRequest scripIdentifierRequest) throws Exception {
        return priceGetterService.getPrices(scripIdentifierRequest);
    }
}
