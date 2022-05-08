package com.relocation.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

//小房表
@Entity
@NoArgsConstructor
@Data
@Table(name = "sb", indexes = {@Index(columnList = "id")})
public class SmallBuilding {
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

    @Column(name = "area", nullable = false)
    int area;

    @Column(name = "level", nullable = false, length = 20)
    String level;

    @Column(name = "uprice", nullable = false)
    int unitPrice;

    @Column(name = "value", nullable = false)
    int value;

    public SmallBuilding(int area, String level, int unitPrice, int value) {
        this.area = area;
        this.level = level;
        this.unitPrice = unitPrice;
        this.value = value;
    }
}
