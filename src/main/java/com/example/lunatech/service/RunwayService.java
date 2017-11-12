package com.example.lunatech.service;

import com.example.lunatech.model.Runway;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Set;

public interface RunwayService {
    List<Runway> findByAirportIdent(String airportIdent);
    Set<String> findSurfacesByAirportIdents(Set<String> airportIdent);
    List<String> findMostPopularIdent();
}
