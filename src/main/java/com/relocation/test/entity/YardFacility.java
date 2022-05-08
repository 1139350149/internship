package com.relocation.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

//院内设施
@Entity
@NoArgsConstructor
@Data
@Table(name = "yf", indexes = {@Index(columnList = "id")})
public class YardFacility {
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
    private String name;

    @Column(name = "unit", nullable = false, length = 10)
    String unit;

    @Column(name = "uprice", nullable = false)
    int unitPrice;

    public YardFacility(String name, String unit, int unitPrice) {
        this.name = name;
        this.unit = unit;
        this.unitPrice = unitPrice;
    }
}
