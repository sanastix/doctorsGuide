package com.example.doctorsguide.services;

import com.example.doctorsguide.data.Form;
import com.example.doctorsguide.repositories.FormRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FormService {

    private final FormRepository formRepository;

    public List<Form> getForms(){
        return formRepository.findAll();
    }

    public Optional<Form> getFormById(Integer id){
        return formRepository.findById(id);
    }

}
