//package com.financebros.portfoliodashboardbackend.pricewatcher.service;
//
//import com.financebros.portfoliodashboardbackend.dto.pricewatcher.ScripIdentifierRequest;
//import com.financebros.portfoliodashboardbackend.dto.pricewatcher.ScripResponse;
//import com.financebros.portfoliodashboardbackend.model.pricewatcher.ScripDocument;
//import com.financebros.portfoliodashboardbackend.model.pricewatcher.ScripKey;
//import com.financebros.portfoliodashboardbackend.pricewatcher.repository.PriceWatcherRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class PriceWatcherService {
//    private final PriceWatcherRepository priceWatcherRepository;
//    public void createPriceWatcher(ScripIdentifierRequest scripIdentifierRequest) {
//        ScripDocument scripDocument = mapToScripDocument(scripIdentifierRequest);
//        priceWatcherRepository.save(scripDocument);
//        log.info("Price Watcher added for {}", scripIdentifierRequest);
//    }
//
//    @Transactional(readOnly = true)
//    public List<ScripResponse> getAllScrips() {
//        List<ScripDocument> scrips = priceWatcherRepository.findAll();
//        log.info("{} scrips returned for watcher", scrips.size());
//        return scrips.stream().map(this::mapToScripResponse).toList();
//    }
//
//    private ScripResponse mapToScripResponse(ScripDocument scripDocument) {
//        return ScripResponse.builder()
//                .scripName(scripDocument.getScrip().getScripName())
//                .exchange(scripDocument.getScrip().getExchange())
//                .type(scripDocument.getScrip().getType())
//                .build();
//    }
//
//    private ScripDocument mapToScripDocument(ScripIdentifierRequest scripIdentifierRequest) {
//        ScripKey scrip = ScripKey.builder()
//                .scripName(scripIdentifierRequest.getScripName())
//                .exchange(scripIdentifierRequest.getExchange())
//                .type(scripIdentifierRequest.getType()).build();
//        return ScripDocument.builder().scrip(scrip).build();
//    }
//
//    public void deleteScrip(ScripIdentifierRequest scripIdentifierRequest) {
//        priceWatcherRepository.delete(mapToScripDocument(scripIdentifierRequest));
//        log.info("Price Watcher removed for {}", scripIdentifierRequest);
//    }
//
//    public void deleteAll() {
//        priceWatcherRepository.deleteAll();
//    }
//}
