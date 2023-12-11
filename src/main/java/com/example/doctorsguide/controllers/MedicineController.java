package com.example.doctorsguide.controllers;

import com.example.doctorsguide.data.ActiveIngredient;
import com.example.doctorsguide.data.Disease;
import com.example.doctorsguide.data.Form;
import com.example.doctorsguide.data.Medicine;
import com.example.doctorsguide.repositories.MedicineRepository;
import com.example.doctorsguide.services.ActiveIngredientService;
import com.example.doctorsguide.services.DiseaseService;
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
import java.util.Set;

@Controller
@AllArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;
    private final ActiveIngredientService activeIngredientService;
    private final FormService formService;
    private final DiseaseService diseaseService;

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
        model.addAttribute("active_ingredients", activeIngredientService.getIngredients());
        model.addAttribute("diseases", diseaseService.getDiseases());
        return "add_medicine";
    }

    @PostMapping("/add_new_medicine")
    public String addMedicine(@RequestParam("name") String name,
                              @RequestParam("forms") Form form,
                              @RequestParam("quantity") int quantity,
                              @RequestParam("active_ingredients") Set<ActiveIngredient> active_ingredients,
                              @RequestParam("dosage") String dosage,
                              @RequestParam("diseases") Set<Disease> disease){
        medicineService.addMedicineToStorage(name, form, quantity, active_ingredients, dosage, disease);
        return "redirect:/storage";
    }

    @GetMapping("/medicine_info/{id}")
    public String medicineById(@PathVariable int id, Model model) {
        Optional<Medicine> med = medicineService.getMedicineById(id);
        if (med.isPresent()) {
            model.addAttribute("med", med.get());
            model.addAttribute("active_ingredient", activeIngredientService.getIngredientsByMedicineId(id));
            model.addAttribute("analogues", activeIngredientService.findAnalogues(id));
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

    @GetMapping("/delete_medicine/{id}")
    public String deleteMedicine(@PathVariable int id){
        medicineService.deleteMedicineById(id);
        return "redirect:/storage";
    }

}
