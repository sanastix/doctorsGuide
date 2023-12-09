package com.example.doctorsguide.services;

import com.example.doctorsguide.data.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientService {

    private final SymptomService symptomService;
    private final DiseaseService diseaseService;
    private final DiagnosticProcedureService diagnosticProcedureService;
    private final MedicineService medicineService;

    public List<String> composeForm(PatientExaminationForm patientForm){
        List<String> form = new ArrayList<>();
        form.add("Name: " + patientForm.getName());
        form.add("Age: " + patientForm.getAge());

        String symptoms = patientForm.getSymptoms().stream()
                .map(s -> symptomService.getSymptomById(Integer.valueOf(s)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Symptom::getName)
                .collect(Collectors.joining(", "));
        form.add("Symptoms: " + symptoms);

        String diseases = Optional.ofNullable(patientForm.getDiseases())
                .orElse(Collections.emptyList())
                .stream()
                .map(d -> diseaseService.getDiseaseById(Integer.valueOf(d)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Disease::getName)
                .collect(Collectors.joining(", "));
        form.add("Diagnosis: " + diseases);

        String procedures = patientForm.getProcedures().stream()
                .map(p -> diagnosticProcedureService.getProcedureById(Integer.valueOf(p)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(DiagnosticProcedure::getName)
                .collect(Collectors.joining(", "));
        form.add("Diagnostic procedures: " + procedures);

        String medicines = patientForm.getMedicines().stream()
                .map(m -> medicineService.getMedicineById(Integer.valueOf(m)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(m -> m.getName() + " " + m.getDosage())
                .collect(Collectors.joining(", "));
        form.add("Treatment: " + medicines);
        return form;
    }

}