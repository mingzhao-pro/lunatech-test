package com.example.lunatech.controller;

import com.example.lunatech.model.Airport;
import com.example.lunatech.model.Country;
import com.example.lunatech.model.Runway;
import com.example.lunatech.service.AirportService;
import com.example.lunatech.service.CountryService;
import com.example.lunatech.service.RunwayService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class QueryAndReportController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private RunwayService runwayService;

    @RequestMapping("/query")
    public Map<Airport, List<Runway>> get(@RequestParam(name = "country") String codeOrName) {
        Map<Airport, List<Runway>> results = new HashMap<>();
        for (Airport airport : countryService.findAirports(codeOrName)) {
            List<Runway> runways = runwayService.findByAirportIdent(airport.getIdent());
            results.put(airport, runways);
        }

        return results;
    }

    @RequestMapping("/report")
    public List<Country> get() {
        List<Country> maxOwners = airportService.findMaxOwner();
        List<Country> minOwners = airportService.findMinOwner();
        maxOwners.addAll(minOwners);
        return maxOwners;
    }

    @RequestMapping("/mostPopularSurface")
    public List<String> getSurface() {
        return runwayService.findMostPopularIdent();
    }
}
