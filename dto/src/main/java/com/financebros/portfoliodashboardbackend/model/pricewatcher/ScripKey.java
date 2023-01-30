package com.financebros.portfoliodashboardbackend.model.pricewatcher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScripKey {
    String scripName;
    String type;
    String exchange;

    public static ScripKey fromScripPricesKey(ScripPricesKey scripPricesKey) {
        return ScripKey.builder()
                .scripName(scripPricesKey.getScrip().getScripName())
                .type(scripPricesKey.getScrip().getType())
                .exchange(scripPricesKey.getScrip().getExchange())
                .build();
    }
}