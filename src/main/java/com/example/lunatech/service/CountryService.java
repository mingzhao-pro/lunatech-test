package com.example.lunatech.service;

import com.example.lunatech.model.Airport;
import com.example.lunatech.model.Country;

import java.util.List;
import java.util.Set;

public interface CountryService {

    Country findByCodeOrName(String codeOrName);

    List<Airport> findAirports(String codeOrName);

    List<Country> findByCodeIn(List<String> codes);

    Set<String> findRunwaySurfaces(String codeOrName);
}
