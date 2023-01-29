package com.financebros.portfoliodashboardbackend.pricewatcher.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScripKey {
    String scripName;
    String type;
    String exchange;
}