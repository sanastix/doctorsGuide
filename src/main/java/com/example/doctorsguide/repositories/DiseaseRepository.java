package com.example.doctorsguide.repositories;

import com.example.doctorsguide.data.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiseaseRepository extends JpaRepository<Disease, Integer> {

    @Query("select d from Disease d where d.name like %:name%")
    List<Disease> findDiseasesByName(String name);

}