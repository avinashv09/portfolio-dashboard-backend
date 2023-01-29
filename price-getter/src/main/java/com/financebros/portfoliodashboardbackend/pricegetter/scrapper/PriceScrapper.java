package com.financebros.portfoliodashboardbackend.pricegetter.scrapper;

import com.financebros.portfoliodashboardbackend.pricegetter.model.ScripPricesDocument;
import com.financebros.portfoliodashboardbackend.pricegetter.service.DemoModel;
import com.financebros.portfoliodashboardbackend.pricewatcher.dto.ScripResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class PriceScrapper {
    public List<ScripPricesDocument> getPrices(ScripResponse scripResponse, Date latestDate) {
        HashMap<String, String> variables = new HashMap<>();
        variables.put("symbol", "TATAMOTORS");
        variables.put("segmentLink", "3");
        variables.put("symbolCount", "1" );
        variables.put("series", "ALL");
        variables.put("dateRange", "day");
        variables.put("dataType", "PRICEVOLUMEDELIVERABLE");
        WebClient webClient = WebClient.builder()
                .baseUrl("https://www1.nseindia.com/products/dynaContent/common/productsSymbolMapping.jsp")
                .defaultHeader("Referer", "https://www1.nseindia.com/products/content/equities/equities/eq_security.htm")
                .defaultUriVariables(variables)
                .build();
        String response;
        ClientResponse demo   = webClient.get().exchange().block(Duration.ofMillis(50000));
        log.info(demo.bodyToMono(String.class).block(Duration.ofMillis(50000)));
        return null;
    }

}
