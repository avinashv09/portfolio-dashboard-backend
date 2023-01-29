package com.financebros.portfoliodashboardbackend.pricegetter.model;

import com.financebros.portfoliodashboardbackend.pricewatcher.model.ScripKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScripPricesKey {
    ScripKey scrip;
    Date date;
}
