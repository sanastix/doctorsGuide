package com.example.doctorsguide.controllers;

import com.example.doctorsguide.data.*;
import com.example.doctorsguide.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@AllArgsConstructor
public class PatientController {

    private final SymptomService symptomService;
    private final DiseaseService diseaseService;
    private final DiagnosticProcedureService diagnosticProcedureService;
    private final MedicineService medicineService;
    private final PatientService patientService;

    @GetMapping("/patient_examination_form")
    public String fillPatientForm(Model model) {
        model.addAttribute("symptoms", symptomService.getSymptoms());
        model.addAttribute("diseases", diseaseService.getDiseases());
        model.addAttribute("procedures", diagnosticProcedureService.getProcedures());
        model.addAttribute("medicines", medicineService.getMedicines());
        return "patient_examination_form";
    }

    @PostMapping("/fill_patient_exam_form")
    public String formReport(@RequestParam("name") String name,
                             @RequestParam("age") int age,
                             @RequestParam("symptoms") Set<Symptom> symptoms,
                             @RequestParam("diseases") Set<Disease> diseases,
                             @RequestParam("procedures") Set<DiagnosticProcedure> procedures,
                             @RequestParam("medicines") Set<Medicine> medicines,
                             @RequestParam(name = "notes", required = false) String notes,
                             Model model) {
        Patient patient = patientService.composePatient(name, age);
        ExaminationForm examinationForm = patientService.composeExaminationForm(symptoms, diseases, procedures, medicines, notes);
        patientService.savePatientAndExaminationForm(patient, examinationForm);
        model.addAttribute("patient", patient);
        model.addAttribute("exam_form", examinationForm);
        return "doctors_report";
    }

}