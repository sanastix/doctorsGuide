package com.example.doctorsguide.repositories;

import com.example.doctorsguide.data.ActiveIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ActiveIngredientRepository extends JpaRepository<ActiveIngredient, Integer> {

    @Query("select a from ActiveIngredient a inner join a.medicines medicines where medicines.id in ?1")
    List<ActiveIngredient> findActiveIngredientsByMedicinesId(Integer id);

}