package com.example.doctorsguide.services;

import com.example.doctorsguide.data.*;
import com.example.doctorsguide.repositories.ExaminationFormRepository;
import com.example.doctorsguide.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ExaminationFormRepository examinationFormRepository;

    public Patient composePatient(String name, int age){
        Patient patient = new Patient();
        patient.setName(name);
        patient.setAge(age);
        return patient;
    }

    public ExaminationForm composeExaminationForm(Set<Symptom> symptoms,
                                                  Set<Disease> diseases,
                                                  Set<DiagnosticProcedure> procedures,
                                                  Set<Medicine> medicines,
                                                  String notes){

        ExaminationForm examinationForm = new ExaminationForm();
        examinationForm.setSymptoms(symptoms);
        examinationForm.setDiseases(diseases);
        examinationForm.setDiagnosticProcedures(procedures);
        examinationForm.setMedicines(medicines);
        examinationForm.setNotes(notes);
        return examinationForm;
    }

    public void savePatientAndExaminationForm(Patient patient, ExaminationForm examinationForm){
        patientRepository.save(patient);
        examinationForm.setPatient(patient);
        examinationFormRepository.save(examinationForm);
    }

}