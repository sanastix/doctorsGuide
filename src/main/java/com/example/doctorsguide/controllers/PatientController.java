package com.example.doctorsguide.controllers;

import com.example.doctorsguide.data.*;
import com.example.doctorsguide.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.stream.Collectors;

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
                             @RequestParam("medicines") Set<Medicine> medicines,
                             @RequestParam(name = "notes", required = false) String notes,
                             Model model) {
        model.addAttribute("exam_form", patientService.composeExaminationForm(name, age, symptoms, diseases, medicines, notes));
        return "doctors_report";
    }

}