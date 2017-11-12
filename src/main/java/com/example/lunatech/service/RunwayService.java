package com.example.lunatech.service;

import com.example.lunatech.model.Runway;

import java.util.List;

public interface RunwayService {
    List<Runway> findByAirportIdent(String airportIdent);
}
