package com.example.lunatech.repository;

import com.example.lunatech.model.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountryRepository extends MongoRepository<Country, String> {

}
