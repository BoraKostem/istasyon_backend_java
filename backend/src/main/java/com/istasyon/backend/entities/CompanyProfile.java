package com.istasyon.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CompanyProfile")
public class CompanyProfile {
    @Id
    private Long id;
    @Column
    private String profilePhotoUrl;
    @Column
    private String S3BucketUrl;
    @Column
    private String aboutMe;
    @Column
    private String CompanyIndustry;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "cUserNo", insertable = false, updatable = false)
    private Company company;
}
