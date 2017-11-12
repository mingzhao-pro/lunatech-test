package com.example.lunatech.service.serviceImpl;

import com.example.lunatech.model.*;
import com.example.lunatech.repository.RunwayRepository;
import com.example.lunatech.service.RunwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service("runwayService")
public class RunwayServiceImpl implements RunwayService {
    @Autowired
    private RunwayRepository runwayRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Runway> findByAirportIdent(String airportIdent) {
        return runwayRepository.findByAirportIdent(airportIdent);
    }

    @Override
    public Set<String> findSurfacesByAirportIdents(Set<String> airportIdents) {
        Query query = new Query();
        query.addCriteria(where("airportIdent").in(airportIdents)).fields().include("surface");
        List<Runway> runways = mongoTemplate.find(query, Runway.class);
        return runways.stream().map(Runway::getSurface).collect(Collectors.toSet());
    }

    @Override
    public List<String> findMostPopularIdent() {
        Aggregation agg = Aggregation.newAggregation(
                group("leIdent").count().as("count"),
                sort(Sort.Direction.DESC, "count"),
                project("count").andExpression("_id").as("leIdent"),
                limit(10)
        );

        AggregationResults<RunwayPopularIdent> results = mongoTemplate.aggregate(agg, Runway.class, RunwayPopularIdent.class);
        return results.getMappedResults().stream().map(RunwayPopularIdent::getLeIdent).collect(Collectors.toList());

    }
}
