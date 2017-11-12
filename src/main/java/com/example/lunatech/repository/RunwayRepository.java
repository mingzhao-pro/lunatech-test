package com.example.lunatech.repository;

import com.example.lunatech.model.Runway;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RunwayRepository extends MongoRepository<Runway, String>{
    List<Runway> findByAirportIdent(String airportIdent);
}
