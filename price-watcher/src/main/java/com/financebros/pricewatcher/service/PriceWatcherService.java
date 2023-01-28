package com.financebros.pricewatcher.service;

import com.financebros.pricewatcher.dto.ScripIdentifierRequest;
import com.financebros.pricewatcher.model.Scrip;
import com.financebros.pricewatcher.repository.PriceWatcherRepository;
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
        Scrip scrip = Scrip.builder()
                .scripName(scripIdentifierRequest.getScripName()).build();
        priceWatcherRepository.save(scrip);
        log.info("Price Watcher added for {}", scripIdentifierRequest);
    }

    public List<Scrip> getAllScrips() {
        return priceWatcherRepository.findAll();
    }
}
