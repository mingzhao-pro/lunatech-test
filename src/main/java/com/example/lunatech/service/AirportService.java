package com.example.lunatech.service;

import com.example.lunatech.model.Airport;
import com.example.lunatech.model.Country;
import com.example.lunatech.model.Runway;
import javafx.util.Pair;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AirportService {
    List<Airport> findByIsoCountry(String isoCountry);

    List<Runway> findRunways(String ident);

    LinkedHashMap<Country, Pair<Integer, Set<String>>> findMaxOwner();

    LinkedHashMap<Country, Pair<Integer, Set<String>>> findMinOwner();
}
