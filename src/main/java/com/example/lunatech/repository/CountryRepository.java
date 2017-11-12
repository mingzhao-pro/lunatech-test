package com.example.lunatech.repository;

import com.example.lunatech.model.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CountryRepository extends MongoRepository<Country, String> {

    List<Country> findByCodeIn(List<String> codes);
}
