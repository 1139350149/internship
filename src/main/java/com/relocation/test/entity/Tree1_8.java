package com.relocation.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

//树1-8年表
@Entity
@NoArgsConstructor
@Data
@Table(name = "t18", indexes = {@Index(columnList = "id")})
public class Tree1_8 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @JsonIgnoreProperties({"compensation"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rpdfcid")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private RelocationPeopleDwellingFacilityCompensation compensation;

    @Column(name = "name", nullable = false, length = 20)
    String name;

    @Column(name = "count", nullable = false)
    int count;

    @Column(name = "uppyear", nullable = false)
    int unitPricePerYear;

    @Column(name = "value", nullable = false)
    int value;

    public Tree1_8(String name, int count, int unitPricePerYear, int value) {
        this.name = name;
        this.count = count;
        this.unitPricePerYear = unitPricePerYear;
        this.value = value;
    }
}
