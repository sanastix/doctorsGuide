package com.example.doctorsguide.services;

import com.example.doctorsguide.data.ActiveIngredient;
import com.example.doctorsguide.repositories.ActiveIngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActiveIngredientService {

    private final ActiveIngredientRepository activeIngredientRepository;

    public Optional<ActiveIngredient> getIngredientById(Integer id){
        return activeIngredientRepository.findById(id);
    }

    public List<ActiveIngredient> getIngredientsByMedicineId(Integer id){
        return activeIngredientRepository.findActiveIngredientsByMedicinesId(id);
    }

}
