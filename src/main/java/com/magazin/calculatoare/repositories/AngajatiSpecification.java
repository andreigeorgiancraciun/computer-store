package com.magazin.calculatoare.repositories;

import com.magazin.calculatoare.entities.Angajat;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

@UtilityClass
public class AngajatiSpecification {

    public static Specification<Angajat> numeContainsIgnoreCase(String keyword) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nume")),
                        "%" + keyword.toLowerCase() + "%"
                );
    }

    public static Specification<Angajat> prenumeContainsIgnoreCase(String keyword) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("prenume")),
                        "%" + keyword.toLowerCase() + "%"
                );
    }

    public static Specification<Angajat> functieContainsIgnoreCase(String keyword) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("functie")),
                        "%" + keyword.toLowerCase() + "%"
                );
    }

    public static Specification<Angajat> salariuBetween(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(
                        root.get("salariu"),
                        minPrice, maxPrice
                );
    }

    public static Specification<Angajat> dataAngajariiBetween(LocalDate dataAngajariiMin, LocalDate dataAngajariiMax) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(
                        root.get("dataAngajarii"),
                        dataAngajariiMin, dataAngajariiMax
                );
    }

}
