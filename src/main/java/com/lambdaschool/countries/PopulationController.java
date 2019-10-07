package com.lambdaschool.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/population")

public class PopulationController {

    // localhost: 2019/population/size/{people}
    @RequestMapping(value = "/size/{people}",
            produces = {"application/json"})
    public ResponseEntity<?> getPopulation(@PathVariable int people) {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList.
                findCountries(c -> c.getPopulation() >= people);
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    // localhost:2019/population/min
    @RequestMapping(value = "/min",
            produces = {"application/json"})
    public ResponseEntity<?> getMinPopulation() {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList.
                findCountries(c -> c.getPopulation() > 0);
        rtnCountries.sort((c1, c2) -> (c1.getPopulation() - c2.getPopulation()));
        return new ResponseEntity<>(rtnCountries.get(0), HttpStatus.OK);
    }

    // localhost:2019/population/max
    @RequestMapping(value = "/max",
            produces = {"application/json"})
    public ResponseEntity<?> getMaxPopulation() {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList.
                findCountries(c -> c.getPopulation() > 0);
        rtnCountries.sort((c1, c2) -> (c2.getPopulation() - c1.getPopulation()));
        return new ResponseEntity<>(rtnCountries.get(0), HttpStatus.OK);
    }
}
