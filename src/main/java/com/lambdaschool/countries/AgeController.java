package com.lambdaschool.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController


@RequestMapping("/age")
public class AgeController {
    // localhost:2019/age/age/{age}
    @RequestMapping(value = {"/age/{age}"},
            produces = {"application/json"})
    public ResponseEntity<?> getMedianAge(@PathVariable int age) {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList.
                findCountries(c -> c.getMedianAge() >= age);
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    // localhost:2019/age/min
    @RequestMapping(value = "/min",
            produces = {"application/json"})
    public ResponseEntity<?> getMinAge() {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList.
                findCountries(c -> c.getMedianAge() > 0);

        // only difference from max is c1 and c2 swapped
        rtnCountries.sort((c1, c2) -> c1.getMedianAge() - c2.getMedianAge());
        return new ResponseEntity<>(rtnCountries.get(0), HttpStatus.OK);
    }

    // localhost:2019/age/max
    @RequestMapping(value = "/max",
            produces = {"application/json"})
    public ResponseEntity<?> getMaxAge() {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList.
                findCountries(c -> c.getMedianAge() > 0);

        // only difference from min is c1 and c2 swapped
        rtnCountries.sort((c1, c2) -> c2.getMedianAge() - c1.getMedianAge());
        return new ResponseEntity<>(rtnCountries.get(0), HttpStatus.OK);
    }
}
