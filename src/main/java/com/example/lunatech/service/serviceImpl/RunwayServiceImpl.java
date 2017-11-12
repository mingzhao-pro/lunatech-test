package com.example.lunatech.service.serviceImpl;

import com.example.lunatech.model.Runway;
import com.example.lunatech.repository.RunwayRepository;
import com.example.lunatech.service.RunwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("runwayService")
public class RunwayServiceImpl implements RunwayService {
    @Autowired
    private RunwayRepository runwayRepository;

    @Override
    public List<Runway> findByAirportIdent(String airportIdent) {
        return runwayRepository.findByAirportIdent(airportIdent);
    }
}
