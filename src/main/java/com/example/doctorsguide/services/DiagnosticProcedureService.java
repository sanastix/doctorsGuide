package com.example.doctorsguide.services;

import com.example.doctorsguide.data.DiagnosticProcedure;
import com.example.doctorsguide.repositories.DiagnosticProcedureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DiagnosticProcedureService {

    private final DiagnosticProcedureRepository diagnosticProcedureRepository;

    public Optional<DiagnosticProcedure> getProcedureById(Integer id){
        return diagnosticProcedureRepository.findById(id);
    }

    public List<DiagnosticProcedure> getProcedures(){
        return diagnosticProcedureRepository.findAll();
    }

    public List<DiagnosticProcedure> getProceduresByDiseaseId(Integer id){
        return diagnosticProcedureRepository.findProceduresByDiseaseId(id);
    }

}
