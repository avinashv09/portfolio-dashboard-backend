package com.financebros.portfolio.clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClockConfig {
    private static String hostname = "localhost";
    private static int port = 8080;
    private static long tickValue = 10000L;
    @Bean
    public static String getHostname() {
        return hostname;
    }
    @Bean
    public static int getPort() {
        return port;
    }
    @Bean
    public static long getTickValue() {
        return tickValue;
    }
}
