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
    public List<Airport> get(@RequestParam(name = "country") String codeOrName) {
        List<Airport> results = new ArrayList<>();
        for (Airport airport : countryService.findAirports(codeOrName)){
            List<Runway> runways = runwayService.findByAirportIdent(airport.getIdent());
            airport.setRunways(runways);
            results.add(airport);
        }

        return results;
    }

    @RequestMapping("/report")
    public List<List<Country>> get() {
        List<Country> maxOwners = airportService.findMaxOwner();
        List<Country> minOwners = airportService.findMinOwner();
        return Arrays.asList(maxOwners, minOwners);
    }

    @RequestMapping("/mostPopularSurface")
    public List<String> getSurface() {
        return runwayService.findMostPopularIdent();
    }
}
