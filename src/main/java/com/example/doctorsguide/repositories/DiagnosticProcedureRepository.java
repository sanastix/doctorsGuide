package com.example.doctorsguide.repositories;

import com.example.doctorsguide.data.DiagnosticProcedure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticProcedureRepository extends JpaRepository<DiagnosticProcedure, Integer> {
}