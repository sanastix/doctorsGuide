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
    @Column(name = "medicine_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    private Form form;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

/*    @OneToMany(mappedBy = "medicine")
    private Set<ActiveIngredient> activeIngredientSet = new LinkedHashSet<>();*/

}