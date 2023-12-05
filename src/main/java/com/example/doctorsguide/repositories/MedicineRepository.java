package com.example.doctorsguide.repositories;

import com.example.doctorsguide.data.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {

    @Query("SELECT m FROM Medicine m WHERE m.name = :name")
    List<Medicine> findMedicinesByName(@Param("name") String name);

}