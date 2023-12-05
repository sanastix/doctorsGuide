package com.example.doctorsguide.repositories;

import com.example.doctorsguide.data.ActiveIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveIngredientRepository extends JpaRepository<ActiveIngredient, Integer> {
}