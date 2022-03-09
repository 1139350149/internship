package com.relocation.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

//特殊果树
@Entity
@NoArgsConstructor
@Data
@Table(name = "sf", indexes = {@Index(columnList = "id")})
public class SpecialFruiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @JsonIgnoreProperties({"compensation"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rpdfcid")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private RelocationPeopleDwellingFaciltyCompensation compensation;

    @Column(name = "name", nullable = false, length = 20)
    String name;

    @Column(name = "count", nullable = false)
    int count;

    @Column(name = "uprice", nullable = false)
    int unitPrice;

    @Column(name = "value", nullable = false)
    int value;

    public SpecialFruiter(String name, int count, int unitPrice, int value) {
        this.name = name;
        this.count = count;
        this.unitPrice = unitPrice;
        this.value = value;
    }
}
