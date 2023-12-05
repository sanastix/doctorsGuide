package com.example.doctorsguide.repositories;

import com.example.doctorsguide.data.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, Integer> {
}