package com.financebros.pricewatcher.controller;

import com.financebros.pricewatcher.dto.ScripIdentifierRequest;
import com.financebros.pricewatcher.service.PriceWatcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.financebros.pricewatcher.Constants.*;

@RestController
@RequestMapping(API_END_POINT)
@RequiredArgsConstructor
public class PriceController {
    private final PriceWatcherService priceWatcherService;
    @PostMapping(ADD_END_POINT)
    @ResponseStatus(HttpStatus.CREATED)
    public void addWatcher(@RequestBody ScripIdentifierRequest scripIdentifierRequest){
        priceWatcherService.createPriceWatcher(scripIdentifierRequest);
    }

    @GetMapping(GET_END_POINT)
    @ResponseStatus(HttpStatus.OK)
    public void getAllScrips() {
        priceWatcherService.getAllScrips();
    }
}
