package com.example.doctorsguide.repositories;

import com.example.doctorsguide.data.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {

    @Query("select m from Medicine m where m.name = :name")
    List<Medicine> findMedicinesByName(String name);

}