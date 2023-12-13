package com.example.doctorsguide.controllers;

import com.example.doctorsguide.data.DiagnosticProcedure;
import com.example.doctorsguide.data.Disease;
import com.example.doctorsguide.data.Medicine;
import com.example.doctorsguide.data.Symptom;
import com.example.doctorsguide.repositories.DiseaseRepository;
import com.example.doctorsguide.services.DiagnosticProcedureService;
import com.example.doctorsguide.services.DiseaseService;
import com.example.doctorsguide.services.MedicineService;
import com.example.doctorsguide.services.SymptomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class DiseaseController {

    private final DiseaseService diseaseService;
    private final SymptomService symptomService;
    private final DiagnosticProcedureService diagnosticProcedureService;
    private final MedicineService medicineService;

    private final DiseaseRepository diseaseRepository;

    @GetMapping("/diseases")
    public String showDiseases(Model model){
        model.addAttribute("diseases", diseaseService.getDiseases());
        return "diseases";
    }

    @GetMapping("/diseases/{id}")
    public String diseaseInfo(@PathVariable int id, Model model){
        Optional<Disease> disease = diseaseService.getDiseaseById(id);
        if (disease.isPresent()){
            model.addAttribute("disease", disease.get());
            return "disease_info";
        } else {
            return "error_page";
        }
    }

    @GetMapping("/disease_info/{id}")
    public String diseaseById(@PathVariable int id, Model model){
        Optional<Disease> disease = diseaseService.getDiseaseById(id);
        List<Symptom> symptoms = symptomService.getSymptomsByDiseaseId(id);
        List<DiagnosticProcedure> procedures = diagnosticProcedureService.getProceduresByDiseaseId(id);
        List<Medicine> medicines = medicineService.getMedicinesByDiseaseId(id);
        if (disease.isPresent()){
            model.addAttribute("disease", disease.get());
            model.addAttribute("symptom", symptoms);
            model.addAttribute("procedure", procedures);
            model.addAttribute("medicine", medicines);
            return "disease_info";
        } else {
            return "error_page";
        }
    }

    @GetMapping("/find_diseases_by_name")
    public String findDisease(@RequestParam("name") String name, Model model){
        List<Disease> diseases = diseaseRepository.findDiseasesByName(name);
        model.addAttribute("diseases", diseases);
        return "diseases";
    }

}
