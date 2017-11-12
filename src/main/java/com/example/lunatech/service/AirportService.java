package com.example.lunatech.service;

import com.example.lunatech.model.Airport;
import com.example.lunatech.model.Runway;

import java.util.List;

public interface AirportService {
    List<Airport> findByIsoCountry(String isoCountry);
    List<Runway> findRunways(String ident);
}
