package com.example.doctorsguide.controllers;

import com.example.doctorsguide.data.ActiveIngredient;
import com.example.doctorsguide.data.Form;
import com.example.doctorsguide.data.Medicine;
import com.example.doctorsguide.repositories.MedicineRepository;
import com.example.doctorsguide.services.ActiveIngredientService;
import com.example.doctorsguide.services.FormService;
import com.example.doctorsguide.services.MedicineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;
    private final ActiveIngredientService activeIngredientService;
    private final FormService formService;

    private final MedicineRepository medicineRepository;

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
        if (medicine.isPresent()){
            model.addAttribute("med", medicine.get());
            return "medicine_edit";
        } else {
            return "medicine_not_found";
        }
    }

    @PostMapping("/edit_medicine")
    public String changeMedicine(@RequestParam("id") int id, @RequestParam int quantity){
        medicineService.changeMedicineQuantity(quantity, id);
        return "redirect:/storage";
    }

    @GetMapping("/add_medicine")
    public String addMedicine(Model model){
        model.addAttribute("forms", formService.getForms());
        return "add_medicine";
    }

    @PostMapping("/add_new_medicine")
    public String addMedicine(@RequestParam String name, Form form, int quantity){
        medicineService.addMedicineToStorage(name, form,quantity);
        //make this function
        return "redirect:/storage";
    }

    @GetMapping("/medicine_info/{id}")
    public String medicineById(@PathVariable int id, Model model){
        Optional<Medicine> medicine = medicineService.getMedicineById(id);
        if (medicine.isPresent()){
            model.addAttribute("med", medicine.get());
            model.addAttribute("active_ingredient", activeIngredientService.getIngredientsByMedicineId(id));
            return "medicine_info";
        } else {
            return "medicine_not_found";
        }
    }

    @GetMapping("/find_medicines_by_name")
    public String findMedicine(@RequestParam("name") String name, Model model){
        model.addAttribute("storage", medicineRepository.findMedicinesByName(name));
        return "storage";
    }

}
