//package com.financebros.portfoliodashboardbackend.pricegetter.repository;
//
//import com.financebros.portfoliodashboardbackend.model.pricewatcher.ScripPricesDocument;
//import com.financebros.portfoliodashboardbackend.model.pricewatcher.ScripPricesKey;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface ScripPricesRepository extends MongoRepository<ScripPricesDocument, ScripPricesKey> {
//
//    @Query("{ '_id.scrip.scripName' : ?0}")
//    List<ScripPricesDocument> findByScripName(String scripName, Pageable pageable);
//
//    @Query("{ '_id.scrip.scripName' : ?0, '_id.scrip.exchange' : ?1, '_id.scrip.type' : ?2}")
//    List<ScripPricesDocument> findByScrip(String scripName, String exchange, String type);
//}
