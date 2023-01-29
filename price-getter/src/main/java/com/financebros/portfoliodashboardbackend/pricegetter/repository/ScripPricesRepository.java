package com.financebros.portfoliodashboardbackend.pricegetter.repository;

import com.financebros.portfoliodashboardbackend.pricegetter.model.ScripPricesDocument;
import com.financebros.portfoliodashboardbackend.pricegetter.model.ScripPricesKey;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScripPricesRepository extends MongoRepository<ScripPricesDocument, ScripPricesKey> {
}
