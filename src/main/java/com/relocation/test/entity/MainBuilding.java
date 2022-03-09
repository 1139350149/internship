package com.relocation.test.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

//主房屋表
@Entity
@NoArgsConstructor
@Data
@Table(name = "mb", indexes = {@Index(columnList = "id")})
public class MainBuilding {
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

    @Column(name = "rcount", nullable = false)
    int roomCount;

    @Column(name = "area", nullable = false)
    int area;

    @Column(name = "level", nullable = false, length = 20)
    String level;

    @Column(name = "uprice", nullable = false)
    int unitPrice;

    @Column(name = "value")
    int value;

    public MainBuilding(int roomCount, int area, String level, int unitPrice, int value) {
        this.roomCount = roomCount;
        this.area = area;
        this.level = level;
        this.unitPrice = unitPrice;
        this.value = value;
    }
}
