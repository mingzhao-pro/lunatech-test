package com.example.lunatech;

import com.example.lunatech.model.Country;
import com.example.lunatech.service.CountryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LunatechApplicationTests {

    @Autowired
    CountryService countryService;


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


}
