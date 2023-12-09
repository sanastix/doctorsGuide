package com.example.doctorsguide.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "medicine")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    private Form form;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "dosage", nullable = false)
    private String dosage;
    
    @ManyToMany(mappedBy = "medicines")
    private Set<ActiveIngredient> activeIngredients = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "medicine_disease",
            joinColumns = @JoinColumn(name = "medicine_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id"))
    private Set<Disease> diseases = new LinkedHashSet<>();


}