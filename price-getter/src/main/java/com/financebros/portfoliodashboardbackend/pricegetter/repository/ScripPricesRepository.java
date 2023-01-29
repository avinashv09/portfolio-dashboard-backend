package com.financebros.portfoliodashboardbackend.pricegetter.repository;

import com.financebros.portfoliodashboardbackend.pricegetter.model.ScripPricesDocument;
import com.financebros.portfoliodashboardbackend.pricegetter.model.ScripPricesKey;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ScripPricesRepository extends MongoRepository<ScripPricesDocument, ScripPricesKey> {

    @Query("{ '_id.scrip.scripName' : ?0")
    List<ScripPricesDocument> findByScripName(String scripName, Pageable pageable);
}
