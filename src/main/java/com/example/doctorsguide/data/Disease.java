package com.example.doctorsguide.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "disease")
public class Disease {
    @Id
    @Column(name = "disease_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

}