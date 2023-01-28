package com.financebros.pricewatcher.repository;

import com.financebros.pricewatcher.model.Scrip;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PriceWatcherRepository extends MongoRepository<Scrip, String> {
}
