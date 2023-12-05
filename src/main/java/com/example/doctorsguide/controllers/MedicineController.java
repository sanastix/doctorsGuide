package com.example.doctorsguide.controllers;

import com.example.doctorsguide.data.Medicine;
import com.example.doctorsguide.services.MedicineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping("/storage")
    public String showMedicines(Model model){
        model.addAttribute("storage", medicineService.getMedicines());
        return "storage";
    }

    @GetMapping("/storage/{id}")
    public String medicineInfo(@PathVariable int id, Model model){
        Optional<Medicine> medicine = medicineService.getMedicineById(id);
        if (medicine.isPresent()){
            model.addAttribute("medicine", medicine.get());
            return "medicine_info";
        } else {
            return "medicine_not_found";
        }
    }

    @GetMapping("/medicine_edit/{id}")
    public String editMedicine(@PathVariable int id, Model model){
        Optional<Medicine> medicine = medicineService.getMedicineById(id);
        //make this function
        return "medicine_edit";
    }

    @PostMapping("/add_medicine")
    public String addMedicineToStorage(){
        //make this function
        return "add_medicine";
    }

    @GetMapping("/medicine_info/{id}")
    public String medicineById(@PathVariable int id, Model model){
        Optional<Medicine> medicine = medicineService.getMedicineById(id);
        if (medicine.isPresent()){
            model.addAttribute("med", medicine.get());

            //make this function
            return "medicine_info";
        } else {
            return "medicine_not_found";
        }
    }

}
