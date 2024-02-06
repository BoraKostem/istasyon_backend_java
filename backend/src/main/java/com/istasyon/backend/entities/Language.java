package com.istasyon.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Language")
public class Language {

    @Id
    private Integer languageId;

    @Column(length = 20)
    private String languageName;

    // Getters and setters...
}
