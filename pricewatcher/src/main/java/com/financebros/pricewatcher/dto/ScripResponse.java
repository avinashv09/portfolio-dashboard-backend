package com.financebros.pricewatcher.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ScripResponse {
    private String scripName;
    private String exchange;
    private String type;
}
