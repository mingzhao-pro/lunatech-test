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

import java.util.ArrayList;
import java.util.List;

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

        List<AirportOwner> airportOwners = results.getMappedResults();
        List<String> codes = new ArrayList<>();
        for (AirportOwner owner : airportOwners) {
            codes.add(owner.getMaxOwner());
        }
        return countryService.findByCodeIn(codes);
    }

    @Override
    public Pair<Integer, List<Country>> findMinOwner() {

        Aggregation agg = newAggregation(
                group("isoCountry").count().as("count"),
                group("count").addToSet("_id").as("minOwner"),
                sort(Sort.Direction.ASC, "_id"),
                project("minOwner").andExpression("_id").as("count"),
                limit(1)
        );

        AirportOwner result = mongoTemplate.aggregate(agg, Airport.class, AirportOwner.class).getUniqueMappedResult();
        List<Country> countries = countryService.findByCodeIn(result.getMinOwner());
        return new Pair<>(result.getCount(), countries);
    }
}
































