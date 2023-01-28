package com.financebros.pricewatcher.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "priceWatcherUniverse")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Scrip {
    @Id
    String id;
    String scripName;
    String type;
    String exchange;
}