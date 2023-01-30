package com.financebros.portfoliodashboardbackend.model.pricewatcher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "scrip-prices")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ScripPricesDocument {
    @Id
    ScripPricesKey scrip;
    double closePrice;
    double prevClosePrice;
}
