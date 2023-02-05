//package com.financebros.portfoliodashboardbackend.pricewatcher.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.financebros.portfoliodashboardbackend.dto.pricewatcher.ScripIdentifierRequest;
//import com.financebros.portfoliodashboardbackend.dto.pricewatcher.ScripResponse;
//import com.financebros.portfoliodashboardbackend.pricewatcher.Constants;
//import com.financebros.portfoliodashboardbackend.pricewatcher.service.PriceWatcherService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(Constants.API_END_POINT)
//@RequiredArgsConstructor
//public class PriceController {
//    private final ObjectMapper objectMapper;
//    private final PriceWatcherService priceWatcherService;
//    @PostMapping(Constants.ADD_END_POINT)
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addWatcher(@RequestBody ScripIdentifierRequest scripIdentifierRequest){
//        priceWatcherService.createPriceWatcher(scripIdentifierRequest);
//    }
//
//    @GetMapping(Constants.GET_END_POINT)
//    @ResponseStatus(HttpStatus.OK)
//    public List<ScripResponse> getAllScrips() {
//        return priceWatcherService.getAllScrips();
//    }
//
//    @DeleteMapping(Constants.DELETE_END_POINT)
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteScrip(@RequestBody ScripIdentifierRequest scripIdentifierRequest){
//        priceWatcherService.deleteScrip(scripIdentifierRequest);
//    }
//
//    @DeleteMapping(Constants.DELETE_ALL_END_POINT)
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteAllScrip(){
//        priceWatcherService.deleteAll();
//    }
//}
