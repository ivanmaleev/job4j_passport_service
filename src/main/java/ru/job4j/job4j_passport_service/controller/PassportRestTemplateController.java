package ru.job4j.job4j_passport_service.controller;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.job4j.job4j_passport_service.entity.Passport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/passport-remote")
public class PassportRestTemplateController {

    private static final String API = "http://localhost:8080/passport/";
    private static final String API_ID = "http://localhost:8080/passport/{id}";

    @Autowired
    private RestTemplate rest;

    @PostMapping("/")
    public ResponseEntity<Passport> save(@RequestBody Passport passport) {
        passport = this.rest.postForObject(API, passport, Passport.class);
        return new ResponseEntity<>(
                passport,
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Passport passport) {
        this.rest.put(API, passport, Passport.class);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rest.delete(API_ID, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public List<Passport> findAll() {
        return rest.exchange(API, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Passport>>() {
                }).getBody();
    }

    @GetMapping(value = "/", params = "seria")
    public List<Passport> findWithSeria(@NotNull @RequestParam String seria) {
        Map<String, String> params = new HashMap<>();
        params.put("seria", seria);
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(API)
                .queryParam("seria", "{seria}")
                .encode()
                .toUriString();
        return rest.exchange(urlTemplate, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Passport>>() {
                }, params).getBody();
    }

    @GetMapping("/unavaliabe")
    public List<Passport> findUnavailable() {
        return rest.exchange(API + "/unavaliabe", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Passport>>() {
                }).getBody();
    }

    @GetMapping("/find-replaceable")
    public List<Passport> findReplaceble() {
        return rest.exchange(API + "/find-replaceable", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Passport>>() {
                }).getBody();
    }
}
