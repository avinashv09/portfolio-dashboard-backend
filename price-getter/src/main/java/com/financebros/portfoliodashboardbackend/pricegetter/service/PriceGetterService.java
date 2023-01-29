package com.financebros.portfoliodashboardbackend.pricegetter.service;

import com.financebros.portfoliodashboardbackend.pricegetter.model.ScripPricesDocument;
import com.financebros.portfoliodashboardbackend.pricegetter.repository.ScripPricesRepository;
import com.financebros.portfoliodashboardbackend.pricegetter.scrapper.PriceScrapper;
import com.financebros.portfoliodashboardbackend.pricewatcher.dto.ScripResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PriceGetterService {
    private final PriceScrapper priceScrapper;
    private final ScripPricesRepository scripPricesRepository;

    public void updatePrices(){
        List<ScripResponse> scrips = getAllWatchedScrips();
        for(ScripResponse scripResponse : scrips) {
            updatePrice(scripResponse);
        }
    }

    private List<ScripResponse> getAllWatchedScrips() {
        return null;
    }

    private void updatePrice(ScripResponse scripResponse) {
        Date latestDate = getLatestDate(scripResponse);
        List<ScripPricesDocument> scripPricesDocumentList = priceScrapper.getPrices(scripResponse, latestDate);
        scripPricesRepository.saveAll(scripPricesDocumentList);
        log.info("{} Prices updated for scrip {}", scripPricesDocumentList.size(), scripResponse);
    }

    private Date getLatestDate(ScripResponse scripResponse) {
        return null;
    }
}
