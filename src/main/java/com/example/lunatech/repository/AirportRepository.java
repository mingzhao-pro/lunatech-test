package com.example.lunatech.repository;

import com.example.lunatech.model.Airport;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AirportRepository extends MongoRepository<Airport, String>{
    List<Airport> findByIsoCountry(String isoCountry);
}
