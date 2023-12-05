package com.example.doctorsguide.repositories;

import com.example.doctorsguide.data.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymptomRepository extends JpaRepository<Symptom, Integer> {
}