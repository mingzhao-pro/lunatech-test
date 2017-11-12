package com.example.lunatech.service;

import com.example.lunatech.model.Airport;
import com.example.lunatech.model.Country;
import com.example.lunatech.model.Runway;

import java.util.List;
import java.util.Map;

public interface CountryService {

    Country findByCodeOrName(String codeOrName);

    List<Airport> findAirports(String codeOrName);

    Map<Airport, List<Runway>> findAirportsAndAssociatedRunways(String codeOrName);
}
