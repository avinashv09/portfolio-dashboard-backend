package com.financebros.portfoliodashboardbackend.dto.pricegetter;

import com.financebros.portfoliodashboardbackend.model.pricewatcher.ScripKey;
import com.financebros.portfoliodashboardbackend.model.pricewatcher.ScripPricesDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScripPricesResponse {
    ScripKey scripKey;
    Date date;
    double prevClose;
    double closePrice;

    public static ScripPricesResponse fromScripPricesDocument(ScripPricesDocument scripPricesDocument) {
        return ScripPricesResponse.builder()
                .scripKey(ScripKey.fromScripPricesKey(scripPricesDocument.getScrip()))
                .date(scripPricesDocument.getScrip().getDate())
                .prevClose(scripPricesDocument.getPrevClosePrice())
                .closePrice(scripPricesDocument.getClosePrice())
                .build();
    }
}
