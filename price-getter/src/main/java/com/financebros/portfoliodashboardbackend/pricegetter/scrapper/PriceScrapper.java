package com.financebros.portfoliodashboardbackend.pricegetter.scrapper;

import com.financebros.portfoliodashboardbackend.pricegetter.model.ScripPricesDocument;
import com.financebros.portfoliodashboardbackend.pricegetter.model.ScripPricesKey;
import com.financebros.portfoliodashboardbackend.pricewatcher.dto.ScripResponse;
import com.financebros.portfoliodashboardbackend.pricewatcher.model.ScripKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class PriceScrapper {
    public List<ScripPricesDocument> getPrices(ScripResponse scripResponse, Date latestDate) throws ParseException {
        SimpleDateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy");
        String dateRange = latestDate == null ? "24month" : "";
        String dateFrom = latestDate == null ? "" : dateFormater.format(latestDate);
        String dateTo = latestDate == null ? "" : dateFormater.format(new Date());
        String symbol = scripResponse.getScripName();
        String url = String.format("https://www1.nseindia.com/products/dynaContent/common/productsSymbolMapping.jsp?dataType=PRICEVOLUMEDELIVERABLE&dateRange=%s&fromDate=%s&toDate=%s&segmentLink=3&symbolCount=1&series=ALL&symbol=%s",
                dateRange,
                dateFrom,
                dateTo,
                symbol);
        log.info("Querying url: {}", url);
        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader("Referer", "https://www1.nseindia.com/products/content/equities/equities/eq_security.htm")
                .defaultHeader("host", "www1.nseindia.com")
                .build();
        String htmlResponse = webClient.get().retrieve().bodyToMono(String.class).block();
        Document document = Jsoup.parse(htmlResponse);
        String csvString = document.getElementById("csvContentDiv").text().replace("\"", "");
        String[] lines = csvString.split(":");
        LinkedList<HashMap<String, String>> table = new LinkedList<>();
        String[] headers = lines[0].split(",");
        LinkedList<ScripPricesDocument> scripPricesDocuments = new LinkedList<>();
        for(int i = 1; i < lines.length; i++) {
            HashMap<String, String> map = new HashMap<>();
            String[] fields = lines[i].split(",");
            for(int j = 0; j < fields.length; j++)  {
                map.put(headers[j], fields[j]);
            }
            ScripKey scrip = ScripKey.builder()
                    .scripName(scripResponse.getScripName())
                    .exchange(scripResponse.getExchange())
                    .type(scripResponse.getType()).build();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

            ScripPricesKey scripPricesKey =  ScripPricesKey.builder()
                    .scrip(scrip)
                    .date(dateFormat.parse(map.get("Date")))
                    .build();
            ScripPricesDocument scripPricesDocument = ScripPricesDocument.builder()
                    .closePrice(Double.parseDouble(map.get("Close Price")))
                    .prevClosePrice(Double.parseDouble(map.get("Prev Close")))
                    .scrip(scripPricesKey)
                    .build();
            log.info("{}-{}:{}", map.get("Symbol"), map.get("Date"), map.get("Close Price"));
            scripPricesDocuments.add(scripPricesDocument);
        }
        return scripPricesDocuments.stream().toList();
    }

}
