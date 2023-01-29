package com.financebros.portfoliodashboardbackend.pricegetter.scrapper;

import com.financebros.portfoliodashboardbackend.pricegetter.model.ScripPricesDocument;
import com.financebros.portfoliodashboardbackend.pricewatcher.dto.ScripResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PriceScrapper {
    private RestTemplateBuilder restTemplateBuilder;

    private final RestTemplate restTemplate = restTemplate();

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder.setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(5000))
                .defaultHeader("Referer", "https://www1.nseindia.com/products/content/equities/equities/eq_security.htm")
                .build();
    }

    public List<ScripPricesDocument> getPrices(ScripResponse scripResponse, Date latestDate) {
        String userJson = restTemplate.getForObject("https://www1.nseindia.com/products/dynaContent/common/productsSymbolMapping.jsp?symbol=TATAMOTORS&segmentLink=3&symbolCount=1&series=ALL&dateRange=day&fromDate=&toDate=&dataType=PRICEVOLUMEDELIVERABLE", String.class);
        log.info(userJson);
        return null;
    }

}
