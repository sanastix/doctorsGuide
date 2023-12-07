package com.example.doctorsguide.repositories;

import com.example.doctorsguide.data.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SymptomRepository extends JpaRepository<Symptom, Integer> {

    @Query("select s from Symptom s inner join s.diseases diseases where diseases.id in ?1")
    List<Symptom> findSymptomsByDiseasesId(Integer id);

}