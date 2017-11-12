package com.example.lunatech;

import com.example.lunatech.model.Airport;
import com.example.lunatech.model.Country;
import com.example.lunatech.model.Runway;
import com.example.lunatech.service.AirportService;
import com.example.lunatech.service.CountryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LunatechApplicationTests {

    @Autowired
    private CountryService countryService;

    @Autowired
    private AirportService airportService;

    @Test
    public void findByCodeOrName() {
        //input: code = "AF", output: country of id 302619
        int id = 302619;
        Country country = countryService.findByCodeOrName("af");
        assert (country.getId() == id);

        //input: name = "Afghanistan", output: country of id 302619
        country = countryService.findByCodeOrName("Afghanistan");
        assert (country.getId() == id);

        //input: code = "af", output: country of id 302619
        country = countryService.findByCodeOrName("af");
        assert (country.getId() == id);

        //input: name = "Afgh", output: country of id 302619
        country = countryService.findByCodeOrName("Afgh");
        assert (country.getId() == id);

        //input: name = "hanis", output: country of id 302619
        country = countryService.findByCodeOrName("hanis");
        assert (country.getId() == id);
    }

    @Test
    public void findAssociatedAirports() {
        //input: code = "AW", output: airport of ident: "TNCA"
        List<Airport> airports = countryService.findAirports("AW");
        assert(airports.size() == 1);
        assert(airports.get(0).getIdent().equals("TNCA"));

        //input: code = "BN", output: airports of ident: "WBSB", "WBAK"
        airports = countryService.findAirports("BN");
        assert(airports.size() == 2);

        Set<String> iso_countrySet = new HashSet<>();
        for(Airport airport : airports) {
            iso_countrySet.add(airport.getIdent());
        }
        assert(iso_countrySet.contains("WBSB"));
        assert(iso_countrySet.contains("WBAK"));
    }

    @Test
    public void findAssociatedRunways() {
        //input: name = "Macau", output: runway of ident 237541
        List<Airport> airports = countryService.findAirports("Macau");
        List<Runway> runways = new ArrayList<>();

        for(Airport airport : airports) {
            runways.addAll(airportService.findRunways(airport.getIdent()));
        }

        assert(runways.size() == 1);
        assert(runways.get(0).getId() == 237541);
    }
}
