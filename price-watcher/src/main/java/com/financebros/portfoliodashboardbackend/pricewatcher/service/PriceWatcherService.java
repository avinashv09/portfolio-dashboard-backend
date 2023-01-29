package com.financebros.portfoliodashboardbackend.pricewatcher.service;

import com.financebros.portfoliodashboardbackend.pricewatcher.model.ScripKey;
import com.financebros.portfoliodashboardbackend.pricewatcher.repository.PriceWatcherRepository;
import com.financebros.portfoliodashboardbackend.pricewatcher.dto.ScripIdentifierRequest;
import com.financebros.portfoliodashboardbackend.pricewatcher.model.ScripDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PriceWatcherService {
    private final PriceWatcherRepository priceWatcherRepository;
    public void createPriceWatcher(ScripIdentifierRequest scripIdentifierRequest) {
        ScripKey scrip = ScripKey.builder()
                .scripName(scripIdentifierRequest.getScripName()).build();
        ScripDocument scripDocument = ScripDocument.builder().scrip(scrip).build();
        priceWatcherRepository.save(scripDocument);
        log.info("Price Watcher added for {}", scripIdentifierRequest);
    }

    public List<ScripDocument> getAllScrips() {
        return priceWatcherRepository.findAll();
    }
}
