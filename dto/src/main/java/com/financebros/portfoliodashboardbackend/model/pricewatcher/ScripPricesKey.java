package com.financebros.portfoliodashboardbackend.model.pricewatcher;

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
