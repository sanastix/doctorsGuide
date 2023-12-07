package com.example.doctorsguide.repositories;

import com.example.doctorsguide.data.DiagnosticProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiagnosticProcedureRepository extends JpaRepository<DiagnosticProcedure, Integer> {

    @Query("select p from DiagnosticProcedure p inner join p.diseases diseases where diseases.id in ?1")
    List<DiagnosticProcedure> findProceduresByDiseaseId(Integer id);

}