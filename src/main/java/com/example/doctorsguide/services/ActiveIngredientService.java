package com.example.doctorsguide.services;

import com.example.doctorsguide.data.ActiveIngredient;
import com.example.doctorsguide.data.Medicine;
import com.example.doctorsguide.repositories.ActiveIngredientRepository;
import com.example.doctorsguide.repositories.MedicineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ActiveIngredientService {

    private final ActiveIngredientRepository activeIngredientRepository;
    private final MedicineRepository medicineRepository;

    public List<ActiveIngredient> getIngredients(){
        return activeIngredientRepository.findAll();
    }

    public Optional<ActiveIngredient> getIngredientById(Integer id){
        return activeIngredientRepository.findById(id);
    }

    public List<ActiveIngredient> getIngredientsByMedicineId(Integer id){
        return activeIngredientRepository.findActiveIngredientsByMedicinesId(id);
    }

    public List<Medicine> findAnalogues(Integer id) {
        List<Medicine> meds = new ArrayList<>();
        List<ActiveIngredient> activeIngredients = getIngredientsByMedicineId(id);

        for (ActiveIngredient activeIngredient : activeIngredients) {
            List<Medicine> medicinesForIngredient = medicineRepository.findMedicinesByActiveIngredient(activeIngredient);
            meds.addAll(medicinesForIngredient);
        }

        return meds;
    }

}
