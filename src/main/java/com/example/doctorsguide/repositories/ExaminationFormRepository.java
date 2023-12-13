package com.example.doctorsguide.repositories;

import com.example.doctorsguide.data.ExaminationForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminationFormRepository extends JpaRepository<ExaminationForm, Integer> {
}