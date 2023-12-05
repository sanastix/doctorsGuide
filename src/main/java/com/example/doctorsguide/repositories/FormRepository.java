package com.example.doctorsguide.repositories;

import com.example.doctorsguide.data.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Integer> {
}