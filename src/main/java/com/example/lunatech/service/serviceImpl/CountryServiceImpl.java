package com.example.lunatech.service.serviceImpl;

import com.example.lunatech.model.Airport;
import com.example.lunatech.model.Country;
import com.example.lunatech.model.Runway;
import com.example.lunatech.repository.CountryRepository;
import com.example.lunatech.service.AirportService;
import com.example.lunatech.service.CountryService;
import com.example.lunatech.service.RunwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service("countryService")
public class CountryServiceImpl implements CountryService {

    @Autowired
    private AirportService airportService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RunwayService runwayService;

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
        if(country != null) {
            return airportService.findByIsoCountry(country.getCode());
        }
        return new ArrayList<>();
    }

    @Override
    public List<Country> findByCodeIn(List<String> codes) {
        return countryRepository.findByCodeIn(codes);
    }

    @Override
    public Set<String> findRunwaySurfaces(String codeOrName) {
        List<Airport> airports = findAirports(codeOrName);
        Set<String> airportIdents = new HashSet<>();
        if(airports != null) {
            airportIdents = airports.stream().map(Airport::getIdent).collect(Collectors.toSet());
            return runwayService.findSurfacesByAirportIdents(airportIdents);
        }
        return airportIdents;
    }
}
