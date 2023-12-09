package com.example.doctorsguide.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PatientExaminationForm {

    private String name;
    private int age;
    private List<String> symptoms;
    private List<String> diseases;
    private List<String> procedures;
    private List<String> medicines;

}
