package ru.job4j.job4j_passport_service.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.job4j_passport_service.entity.Passport;

import java.sql.Timestamp;

@Repository
public interface PassportRepository extends CrudRepository<Passport, Integer> {

    Iterable<Passport> findPassportsBySeria(String seria);

    Iterable<Passport> findPassportsByExpirationDateIsLessThan(Timestamp timestamp);

    @Query("from Passport p where p.expirationDate > current_timestamp " +
            "and p.expirationDate <= :maxtime")
    Iterable<Passport> findReplaceble(Timestamp maxtime);
}
