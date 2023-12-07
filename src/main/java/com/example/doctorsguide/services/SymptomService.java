package com.example.doctorsguide.services;

import com.example.doctorsguide.data.Symptom;
import com.example.doctorsguide.repositories.SymptomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SymptomService {

    private final SymptomRepository symptomRepository;

    public Optional<Symptom> getSymptomById(Integer id){
        return symptomRepository.findById(id);
    }

    public List<Symptom> getSymptoms(){
        return symptomRepository.findAll();
    }

    public List<Symptom> getSymptomsByDiseaseId(Integer id){
        return symptomRepository.findSymptomsByDiseasesId(id);
    }

}
