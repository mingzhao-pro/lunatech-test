package com.example.lunatech.service.serviceImpl;

import com.example.lunatech.model.Airport;
import com.example.lunatech.model.AirportOwner;
import com.example.lunatech.model.Country;
import com.example.lunatech.model.Runway;
import com.example.lunatech.repository.AirportRepository;
import com.example.lunatech.service.AirportService;
import com.example.lunatech.service.CountryService;
import com.example.lunatech.service.RunwayService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service("airportService")
public class AirportServiceImpl implements AirportService {

    @Autowired
    private CountryService countryService;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private RunwayService runwayService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Airport> findByIsoCountry(String isoCountry) {
        return airportRepository.findByIsoCountry(isoCountry);
    }

    @Override
    public List<Runway> findRunways(String ident) {
        return runwayService.findByAirportIdent(ident);
    }

    /**
     * find the first 10 countries which has the most airports
     *
     * @return
     */
    @Override
    public List<Country> findMaxOwner() {
        Aggregation agg = newAggregation(
                group("isoCountry").count().as("count"),
                sort(Sort.Direction.DESC, "count"),
                project("count").and("maxOwner").previousOperation(),
                limit(10)
        );

        AggregationResults<AirportOwner> results = mongoTemplate.aggregate(agg, Airport.class, AirportOwner.class);

        Map<String, Integer> nbAirportPerCountry = new HashMap<>();
        List<AirportOwner> airportOwners = results.getMappedResults();
        List<String> codes = new ArrayList<>();

        for (AirportOwner owner : airportOwners) {
            String maxOwner = owner.getMaxOwner();
            codes.add(maxOwner);
            nbAirportPerCountry.put(maxOwner, owner.getCount());
        }

        List<Country> countries = countryService.findByCodeIn(codes);
        for (Country country : countries) {

            Set<String> surfaces = countryService.findRunwaySurfaces(country.getCode())
                    .stream().filter(x -> x != null && !x.isEmpty()).collect(Collectors.toSet()); // remove empty surface
            country.setSurfacesOfRunways(surfaces);
            country.setNbAirport(nbAirportPerCountry.get(country.getCode()));
        }
        return countries;
    }

    @Override
    public List<Country> findMinOwner() {

        Aggregation agg = newAggregation(
                group("isoCountry").count().as("count"),
                group("count").addToSet("_id").as("minOwner"),
                sort(Sort.Direction.ASC, "_id"),
                project("minOwner").andExpression("_id").as("count"),
                limit(1)
        );

        AirportOwner airportOwner = mongoTemplate.aggregate(agg, Airport.class, AirportOwner.class).getUniqueMappedResult();
        int count = airportOwner.getCount();

        List<Country> countries = countryService.findByCodeIn(airportOwner.getMinOwner());
        for (Country country : countries) {
            Set<String> surfaces = countryService.findRunwaySurfaces(country.getCode())
                    .stream().filter(x -> x != null && !x.isEmpty()).collect(Collectors.toSet()); // remove empty surface;
            country.setSurfacesOfRunways(surfaces);
            country.setNbAirport(count);
        }

        return countries;
    }
}
































