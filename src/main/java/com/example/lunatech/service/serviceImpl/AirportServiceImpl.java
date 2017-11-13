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
    public LinkedHashMap<Country, Pair<Integer, Set<String>>> findMaxOwner() {
        Aggregation agg = newAggregation(
                group("isoCountry").count().as("count"),
                sort(Sort.Direction.DESC, "count"),
                project("count").and("maxOwner").previousOperation(),
                limit(10)
        );

        AggregationResults<AirportOwner> results = mongoTemplate.aggregate(agg, Airport.class, AirportOwner.class);

        // <countryCode, airportCount>
        Map<String, Integer> nbPerCountry = new HashMap<>();

        List<AirportOwner> airportOwners = results.getMappedResults();
        List<String> codes = new ArrayList<>();
        for (AirportOwner owner : airportOwners) {
            String maxOwner = owner.getMaxOwner();
            codes.add(maxOwner);
            nbPerCountry.put(maxOwner, owner.getCount());
        }

        List<Country> countries = countryService.findByCodeIn(codes);
        Map<Country, Set<String>> countryWithSurfaces = new HashMap<>();
        for (Country country : countries) {
            Set<String> surfaces = countryService.findRunwaySurfaces(country.getCode());
            countryWithSurfaces.put(country, surfaces);
        }

        LinkedHashMap<Country, Pair<Integer, Set<String>>> result = new LinkedHashMap<>();
        for (Country country : countryWithSurfaces.keySet()) {
            Pair pair = new Pair<>(nbPerCountry.get(country.getCode()), countryWithSurfaces.get(country));
            result.put(country, pair);
        }
        return result;
    }

    @Override
    public LinkedHashMap<Country, Pair<Integer, Set<String>>> findMinOwner() {

        Aggregation agg = newAggregation(
                group("isoCountry").count().as("count"),
                group("count").addToSet("_id").as("minOwner"),
                sort(Sort.Direction.ASC, "_id"),
                project("minOwner").andExpression("_id").as("count"),
                limit(1)
        );

        AirportOwner airportOwner = mongoTemplate.aggregate(agg, Airport.class, AirportOwner.class).getUniqueMappedResult();
        int count = airportOwner.getCount();

        LinkedHashMap<Country, Pair<Integer, Set<String>>> result = new LinkedHashMap<>();
        for (Country country : countryService.findByCodeIn(airportOwner.getMinOwner())) {
            Pair pair = new Pair<>(count, countryService.findRunwaySurfaces(country.getCode()));
            result.put(country, pair);
        }
        return result;
    }
}
































