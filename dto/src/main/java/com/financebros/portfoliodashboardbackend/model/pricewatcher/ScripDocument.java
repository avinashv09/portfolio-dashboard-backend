package com.financebros.portfoliodashboardbackend.model.pricewatcher;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "priceWatcherUniverse")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScripDocument {
    @Id
    ScripKey scrip;
}