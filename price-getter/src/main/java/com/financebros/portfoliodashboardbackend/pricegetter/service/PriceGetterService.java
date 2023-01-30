package com.financebros.portfoliodashboardbackend.pricegetter.service;

import com.financebros.portfoliodashboardbackend.dto.pricegetter.ScripPricesResponse;
import com.financebros.portfoliodashboardbackend.dto.pricewatcher.ScripIdentifierRequest;
import com.financebros.portfoliodashboardbackend.dto.pricewatcher.ScripResponse;
import com.financebros.portfoliodashboardbackend.model.pricewatcher.ScripPricesDocument;
import com.financebros.portfoliodashboardbackend.pricegetter.repository.ScripPricesRepository;
import com.financebros.portfoliodashboardbackend.pricegetter.scrapper.PriceScrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PriceGetterService {
    private final PriceScrapper priceScrapper;
    private final ScripPricesRepository scripPricesRepository;

    public void updatePrice(ScripResponse scripResponse) throws ParseException {
        Date latestDate = getLatestDate(scripResponse);
        List<ScripPricesDocument> scripPricesDocumentList = priceScrapper.getPrices(scripResponse, latestDate);
        scripPricesRepository.saveAll(scripPricesDocumentList);
        log.info("{} Prices updated for scrip {}", scripPricesDocumentList.size(), scripResponse);
    }

    private Date getLatestDate(ScripResponse scripResponse) {
        Pageable pageable = PageRequest.of(0, 1, Sort.by("_id.date").descending());
        List<ScripPricesDocument> result = scripPricesRepository.findByScripName(scripResponse.getScripName(), pageable);
        if(result != null && result.size() == 1) {
            log.info("Scrip Price Data for {} present till {}", result.get(0).getScrip().getScrip(), result.get(0).getScrip().getDate());
            return result.get(0).getScrip().getDate();
        }
        return null;
    }

    public void updatePrices() throws Exception {
        ScripResponse[] priceWatcherResponseArray = WebClient.builder()
                .baseUrl("http://localhost:8080/api/pricewatcher/get-all-watched-scrips")
                .build()
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ScripResponse[].class).log()
                .block();
        for(ScripResponse scripResponse : priceWatcherResponseArray) {
            log.info("{} is getting queried", scripResponse);
            updatePrice(scripResponse);
        }
        log.info("Updated prices for {} scrips", priceWatcherResponseArray.length);
    }

    public List<ScripPricesResponse> getPrices(ScripIdentifierRequest scripIdentifierRequest) {
        return scripPricesRepository.findByScrip(scripIdentifierRequest.getScripName(), scripIdentifierRequest.getExchange(),
                scripIdentifierRequest.getType()).stream().map(ScripPricesResponse::fromScripPricesDocument).toList();
    }
}
