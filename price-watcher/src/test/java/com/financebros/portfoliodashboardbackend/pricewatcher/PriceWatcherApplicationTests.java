package com.financebros.portfoliodashboardbackend.pricewatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financebros.portfoliodashboardbackend.pricewatcher.repository.PriceWatcherRepository;
import com.financebros.portfoliodashboardbackend.pricewatcher.dto.ScripIdentifierRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class PriceWatcherApplicationTests {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PriceWatcherRepository priceWatcherRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
        dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void addPriceWatcher() throws Exception {
//        ScripIdentifierRequest scripIdentifierRequest = getScripRequest();
//        String scripRequestIndentifier = objectMapper.writeValueAsString(scripIdentifierRequest);
//        mockMvc.perform(MockMvcRequestBuilders.post(API_END_POINT + "/" + ADD_END_POINT)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(scripRequestIndentifier))
//                .andExpect(status().isCreated());
//        Assertions.assertEquals(1, priceWatcherRepository.findAll().size());
    }

    private ScripIdentifierRequest getScripRequest() {
        return ScripIdentifierRequest.builder()
                .scripName("TATAMOTORS")
                .exchange("NSE")
                .type("EQUITY")
                .build();
    }
}