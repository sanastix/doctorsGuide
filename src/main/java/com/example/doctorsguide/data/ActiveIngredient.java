package com.example.doctorsguide.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "active_ingredient")
public class ActiveIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "medicine_active_ingredient",
            joinColumns = @JoinColumn(name = "active_ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id"))
    private Set<Medicine> medicines = new LinkedHashSet<>();

}