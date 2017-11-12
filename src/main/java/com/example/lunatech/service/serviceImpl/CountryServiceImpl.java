package com.example.lunatech.service.serviceImpl;

import com.example.lunatech.model.Airport;
import com.example.lunatech.model.Country;
import com.example.lunatech.model.Runway;
import com.example.lunatech.repository.CountryRepository;
import com.example.lunatech.service.AirportService;
import com.example.lunatech.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service("countryService")
public class CountryServiceImpl implements CountryService {

    @Autowired
    private AirportService airportService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * find the country according to code or name
     * code is case insensitive
     * name is partial matching
     * @param codeOrName
     * @return
     */
    @Override
    public Country findByCodeOrName(String codeOrName) {

        // make "code" case insensitive
        Criteria codeCriteria = where("code").regex("(?i)" + codeOrName);

        // make "name" partial match
        Criteria nameCriteria = where("name").regex("[a-zA-Z]{0,}" + codeOrName + "[a-zA-Z]{0,}");
        Criteria criteria = new Criteria();
        criteria.orOperator(codeCriteria, nameCriteria);
        return mongoTemplate.findOne(new Query(criteria), Country.class);
    }

    @Override
    public List<Airport> findAirports(String code) {
        Country country = findByCodeOrName(code);
        return airportService.findByIsoCountry(country.getCode());
    }

    @Override
    public Map<Airport, List<Runway>> findAirportsAndAssociatedRunways(String codeOrName) {
        Map<Airport, List<Runway>> airportAndRunways = new HashMap<>();
        List<Airport> airports = findAirports(codeOrName);
        for (Airport airport : airports) {
            List<Runway> runways = airportService.findRunways(airport.getIdent());
            // runways is less than airport, so some airports don't have a runway
            if (runways == null) {
                runways = new ArrayList<>();
            }
            airportAndRunways.put(airport, runways);
        }
        return airportAndRunways;
    }
}
