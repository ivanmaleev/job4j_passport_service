package ru.job4j.job4j_passport_service.controller;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.job4j_passport_service.entity.Passport;
import ru.job4j.job4j_passport_service.service.PassportService;

import java.util.List;

@RestController
@RequestMapping("/passport")
@AllArgsConstructor
public class PassportController {

    @Autowired
    PassportService passportService;

    @PostMapping("/")
    public ResponseEntity<Passport> save(@RequestBody Passport passport) {
        return new ResponseEntity<>(
                this.passportService.save(passport),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Passport passport) {
        this.passportService.update(id, passport);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Passport passport = new Passport();
        passport.setId(id);
        this.passportService.delete(passport);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public List<Passport> findAll() {
        return passportService.findAll();
    }

    @GetMapping(value = "/", params = "seria")
    public List<Passport> findWithSeria(@NotNull @RequestParam String seria) {
        return passportService.findWithSeria(seria);
    }

    @GetMapping("/unavaliabe")
    public List<Passport> findUnavailable() {
        return passportService.findUnavailable();
    }

    @GetMapping("/find-replaceable")
    public List<Passport> findReplaceble() {
        return passportService.findReplaceble();
    }
}
