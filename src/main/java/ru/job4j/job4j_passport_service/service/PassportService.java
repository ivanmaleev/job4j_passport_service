package ru.job4j.job4j_passport_service.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_passport_service.entity.Passport;
import ru.job4j.job4j_passport_service.repository.PassportRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class PassportService {

    @Autowired
    PassportRepository passportRepository;

    public Passport save(Passport passport) {
        return passportRepository.save(passport);
    }

    public void update(int id, Passport passport) {
        passport.setId(id);
        passportRepository.save(passport);
    }

    public void delete(Passport passport) {
        passportRepository.delete(passport);
    }

    public List<Passport> findAll() {
        return (List<Passport>) passportRepository.findAll();
    }

    public List<Passport> findWithSeria(String seria) {
        return (List<Passport>) passportRepository.findPassportsBySeria(seria);
    }

    public List<Passport> findUnavailable() {
        return (List<Passport>) passportRepository
                .findPassportsByExpirationDateIsLessThan(new Timestamp(System.currentTimeMillis()));
    }

    public List<Passport> findReplaceble() {
        int days = 92;
        Timestamp maxTime = new Timestamp(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(92));
        return (List<Passport>) passportRepository.findReplaceble(maxTime);
    }
}
