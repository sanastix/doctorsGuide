package com.example.doctorsguide.controllers;

import com.example.doctorsguide.services.DiagnosticProcedureService;
import com.example.doctorsguide.services.DiseaseService;
import com.example.doctorsguide.services.MedicineService;
import com.example.doctorsguide.services.SymptomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class PatientController {

    private final SymptomService symptomService;
    private final DiseaseService diseaseService;
    private final DiagnosticProcedureService diagnosticProcedureService;
    private final MedicineService medicineService;

    @GetMapping("/patient_examination_form")
    public String fillPatientForm(Model model){
        model.addAttribute("symptoms", symptomService.getSymptoms());
        model.addAttribute("diseases", diseaseService.getDiseases());
        model.addAttribute("procedures", diagnosticProcedureService.getProcedures());
        model.addAttribute("medicines", medicineService.getMedicines());
        return "patient_examination_form";
    }

}
