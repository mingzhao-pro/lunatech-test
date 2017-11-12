package com.example.lunatech.service.serviceImpl;

import com.example.lunatech.model.Airport;
import com.example.lunatech.model.Runway;
import com.example.lunatech.repository.AirportRepository;
import com.example.lunatech.repository.RunwayRepository;
import com.example.lunatech.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("airportService")
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private RunwayRepository runwayRepository;

    @Override
    public List<Airport> findByIsoCountry(String isoCountry) {
        return airportRepository.findByIsoCountry(isoCountry);
    }

    @Override
    public List<Runway> findRunways(String ident) {
        return runwayRepository.findByAirportIdent(ident);
    }
}
