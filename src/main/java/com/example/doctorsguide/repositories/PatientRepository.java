package com.example.doctorsguide.repositories;

import com.example.doctorsguide.data.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}