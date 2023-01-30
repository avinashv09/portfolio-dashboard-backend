package com.financebros.portfoliodashboardbackend.dto.pricewatcher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScripIdentifierRequest {
    private String scripName;
    private String exchange;
    private String type;
}
