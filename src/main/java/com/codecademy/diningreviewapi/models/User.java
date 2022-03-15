package com.codecademy.diningreviewapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String city;
    private String state;
    private String zipCode;
    private Boolean hasPeanutAllergy;
    private Boolean hasEggAllergy;
    private Boolean hasDairyAllergy;

}
