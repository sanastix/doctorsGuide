package com.example.doctorsguide.services;

import com.example.doctorsguide.data.ActiveIngredient;
import com.example.doctorsguide.data.Disease;
import com.example.doctorsguide.data.Form;
import com.example.doctorsguide.data.Medicine;
import com.example.doctorsguide.repositories.ActiveIngredientRepository;
import com.example.doctorsguide.repositories.MedicineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;
    private final ActiveIngredientRepository activeIngredientRepository;

    private final FormService formService;

    public Optional<Medicine> getMedicineById(Integer id){
        return medicineRepository.findById(id);
    }

    public List<Medicine> getMedicines(){
        return medicineRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Medicine::getId))
                .collect(Collectors.toList());
    }

    public List<Medicine> getMedicinesByDiseaseId(Integer id){
        return medicineRepository.findMedicinesByDiseasesId(id);
    }

    public void changeMedicineQuantity(int quantity, int medicineId){
        Medicine medicine = getMedicineById(medicineId).get();
        medicine.setQuantity(quantity);
        medicineRepository.saveAndFlush(medicine);
    }

    public void addMedicineToStorage(String name, Form form, Integer quantity,
                                     Set<ActiveIngredient> active_ingredients, String dosage,
                                     Set<Disease> disease){
        Medicine medicine = new Medicine();
        medicine.setName(name);
        medicine.setForm(form);
        medicine.setQuantity(quantity);

/*        Set<ActiveIngredient> newAISet = convertStringToActiveIngredientSet(active_ingredient);
        List<ActiveIngredient> existingAIList = activeIngredientRepository.findAll();
        Set<ActiveIngredient> activeIngredients = mergeLists(existingAIList, newAISet);
        medicine.setActiveIngredients(activeIngredients);*/
        medicine.setActiveIngredients(active_ingredients);
/*        Set<ActiveIngredient> activeIngredients = new LinkedHashSet<>(active_ingredients);
        medicine.setActiveIngredients(activeIngredients);*/

        medicine.setDosage(dosage);

        medicine.setDiseases(disease);
        medicineRepository.save(medicine);
    }

    private static Set<ActiveIngredient> mergeLists(List<ActiveIngredient> existingList, Set<ActiveIngredient> newSet) {
        Set<ActiveIngredient> existingSet = new HashSet<>(existingList);
        Set<ActiveIngredient> mergedSet = new HashSet<>(existingSet);
        newSet.forEach(newIngredient -> {
            boolean containsIgnoreCase = existingSet.stream()
                    .anyMatch(existingIngredient -> existingIngredient.getName().equalsIgnoreCase(newIngredient.getName()));

            if (!containsIgnoreCase) {
                mergedSet.add(newIngredient);
            }
        });
        return mergedSet;
    }

    private static Set<ActiveIngredient> convertStringToActiveIngredientSet(String inputString) {
        String[] ingredientNames = inputString.split(", ");
        Set<ActiveIngredient> activeIngredientSet = new HashSet<>();
        for (String ingredientName : ingredientNames) {
            ActiveIngredient activeIngredient = new ActiveIngredient();
            activeIngredient.setName(ingredientName);
            activeIngredientSet.add(activeIngredient);
        }
        return activeIngredientSet;
    }

    public void deleteMedicineById(int id) {
        medicineRepository.delete(getMedicineById(id).get());
    }
}
