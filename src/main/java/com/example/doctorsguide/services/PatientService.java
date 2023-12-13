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

    public ExaminationForm composeExaminationForm(String name, int age,
                                                  Set<Symptom> symptoms,
                                                  Set<Disease> diseases,
                                                  Set<Medicine> medicines,
                                                  String notes){
        Patient patient = new Patient();
        patient.setName(name);
        patient.setAge(age);
        patientRepository.save(patient);
        ExaminationForm examinationForm = new ExaminationForm();
        examinationForm.setSymptoms(symptoms);
        examinationForm.setDiseases(diseases);
        examinationForm.setMedicines(medicines);
        examinationForm.setNotes(notes);
        examinationForm.setPatient(patient);
        examinationFormRepository.save(examinationForm);
        return examinationForm;
    }

}