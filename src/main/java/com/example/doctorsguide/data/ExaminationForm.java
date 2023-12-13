package com.example.doctorsguide.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "examination_form")
public class ExaminationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "notes", length = Integer.MAX_VALUE)
    private String notes;

    @ManyToMany
    @JoinTable(name = "examination_form_diseases",
            joinColumns = @JoinColumn(name = "examination_form_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id"))
    private Set<Disease> diseases = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "examination_form_medicines",
            joinColumns = @JoinColumn(name = "examination_form_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id"))
    private Set<Medicine> medicines = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "examination_form_procedures",
            joinColumns = @JoinColumn(name = "examination_form_id"),
            inverseJoinColumns = @JoinColumn(name = "procedure_id"))
    private Set<DiagnosticProcedure> diagnosticProcedures = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "examination_form_symptoms",
            joinColumns = @JoinColumn(name = "examination_form_id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id"))
    private Set<Symptom> symptoms = new LinkedHashSet<>();

}