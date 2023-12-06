package com.example.doctorsguide.controllers;

import com.example.doctorsguide.repositories.DiseaseRepository;
import com.example.doctorsguide.services.DiseaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DiseaseController {

    private final DiseaseService diseaseService;
    private final DiseaseRepository diseaseRepository;

    @GetMapping("/diseases")
    public String showDiseases(Model model){
        model.addAttribute("diseases", diseaseService.getDiseases());
        return "diseases";
    }

}
