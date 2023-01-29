package com.financebros.portfoliodashboardbackend.pricewatcher.repository;

import com.financebros.portfoliodashboardbackend.pricewatcher.model.ScripDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PriceWatcherRepository extends MongoRepository<ScripDocument, String> {
}
