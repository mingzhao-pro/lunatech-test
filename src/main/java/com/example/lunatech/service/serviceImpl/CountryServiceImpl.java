package com.example.lunatech.service.serviceImpl;

import com.example.lunatech.model.Country;
import com.example.lunatech.repository.CountryRepository;
import com.example.lunatech.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service("countryService")
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

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
}
