package com.example.lunatech.service;

import com.example.lunatech.model.Country;

public interface CountryService {

    Country findByCodeOrName(String codeOrName);
}
