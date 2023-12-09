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

    public Optional<ActiveIngredient> getIngredientById(Integer id){
        return activeIngredientRepository.findById(id);
    }

    public List<ActiveIngredient> getIngredientsByMedicineId(Integer id){
        return activeIngredientRepository.findActiveIngredientsByMedicinesId(id);
    }

    public List<Medicine> findAnalogues(Integer id){
        List<Medicine> meds = new ArrayList<>();
        List<ActiveIngredient> activeIngredients = getIngredientsByMedicineId(id);
        for (ActiveIngredient activeIngredient : activeIngredients) {
            meds = medicineRepository.findMedicinesByActiveIngredient(activeIngredient);
        }
        return meds;
    }

/*    public List<String> findAnalogues(int medicineId) {
        List<ActiveIngredient> activeIngredients = getIngredientsByMedicineId(medicineId);
        Set<String> analogueNames = new HashSet<>();

        for (ActiveIngredient activeIngredient : activeIngredients) {
            List<Medicine> medicinesWithSameIngredient =
                    medicineRepository.findMedicinesByActiveIngredient(activeIngredient);

            for (Medicine medicine : medicinesWithSameIngredient) {
                if (medicine.getId() != medicineId) {
                    analogueNames.add(medicine.getName() + " (" + medicine.getForm().getName() + ")");
                }
            }
        }

        return new ArrayList<>(analogueNames);
    }*/

}
