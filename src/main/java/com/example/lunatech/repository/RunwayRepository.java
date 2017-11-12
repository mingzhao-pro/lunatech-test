package com.example.lunatech.repository;

import com.example.lunatech.model.Runway;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Set;

public interface RunwayRepository extends MongoRepository<Runway, String>{
    List<Runway> findByAirportIdent(String airportIdent);
}
