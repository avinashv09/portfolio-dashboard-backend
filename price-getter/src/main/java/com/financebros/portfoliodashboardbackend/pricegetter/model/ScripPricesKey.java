package com.financebros.portfoliodashboardbackend.pricegetter.model;

import com.financebros.portfoliodashboardbackend.pricewatcher.model.ScripKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScripPricesKey {
    ScripKey scrip;
    Date date;
}
