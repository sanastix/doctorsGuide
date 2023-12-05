package com.example.doctorsguide.controllers;

import com.example.doctorsguide.services.MedicineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ActiveIngredientController {

    private final MedicineService medicineService;



}
