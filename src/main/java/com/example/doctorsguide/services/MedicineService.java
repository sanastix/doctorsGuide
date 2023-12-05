package com.example.doctorsguide.services;

import com.example.doctorsguide.data.Form;
import com.example.doctorsguide.data.Medicine;
import com.example.doctorsguide.repositories.MedicineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public List<Medicine> getMedicines(){
        return medicineRepository.findAll();
    }

    public Optional<Medicine> getMedicineById(Integer id){
        return medicineRepository.findById(id);
    }

    public void addMedicineToStorage(String name, Form form, Integer quantity){
        Medicine medicine = new Medicine();
        medicine.setName(name);
        medicine.setForm(form);
        medicine.setQuantity(quantity);
        medicineRepository.save(medicine);
    }

}
