package com.financebros.portfoliodashboardbackend.pricewatcher.controller;

import com.financebros.portfoliodashboardbackend.pricewatcher.Constants;
import com.financebros.portfoliodashboardbackend.pricewatcher.dto.ScripIdentifierRequest;
import com.financebros.portfoliodashboardbackend.pricewatcher.service.PriceWatcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.API_END_POINT)
@RequiredArgsConstructor
public class PriceController {
    private final PriceWatcherService priceWatcherService;
    @PostMapping(Constants.ADD_END_POINT)
    @ResponseStatus(HttpStatus.CREATED)
    public void addWatcher(@RequestBody ScripIdentifierRequest scripIdentifierRequest){
        priceWatcherService.createPriceWatcher(scripIdentifierRequest);
    }

    @GetMapping(Constants.GET_END_POINT)
    @ResponseStatus(HttpStatus.OK)
    public void getAllScrips() {
        priceWatcherService.getAllScrips();
    }
}
