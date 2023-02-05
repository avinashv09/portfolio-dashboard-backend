package com.financebros.portfoliodashboardbackend.pricewatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PriceWatcherApplication {
    public static boolean run = false;

    public static void main(String[] args) {
        SpringApplication.run(PriceWatcherApplication.class, args);
    }
}
