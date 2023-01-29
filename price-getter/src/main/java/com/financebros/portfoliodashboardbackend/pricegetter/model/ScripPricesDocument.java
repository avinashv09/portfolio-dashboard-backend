package com.financebros.portfoliodashboardbackend.pricegetter.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "scrip-prices")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScripPricesDocument {
    @Id
    ScripPricesKey scrip;
    double closePrice;
    double prevClosePrice;
}
