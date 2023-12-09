package com.example.doctorsguide.repositories;

import com.example.doctorsguide.data.ActiveIngredient;
import com.example.doctorsguide.data.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {

    @Query("SELECT m FROM Medicine m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Medicine> findMedicinesByName(String name);

    @Query("select m from Medicine m inner join m.diseases diseases where diseases.id in ?1")
    List<Medicine> findMedicinesByDiseasesId(Integer id);

    @Query("SELECT m FROM Medicine m JOIN m.activeIngredients ai WHERE ai = :activeIngredient")
    List<Medicine> findMedicinesByActiveIngredient(ActiveIngredient activeIngredient);

}