package com.example.doctorsguide.services;

import com.example.doctorsguide.data.Disease;
import com.example.doctorsguide.repositories.DiseaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    public List<Disease> getDiseases(){
        return diseaseRepository.findAll();
    }

    public Optional<Disease> getDiseaseById(Integer id){
        return diseaseRepository.findById(id);
    }

}
