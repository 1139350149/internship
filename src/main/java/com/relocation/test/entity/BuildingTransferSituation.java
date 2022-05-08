package com.relocation.test.entity;

import javax.persistence.*;

import lombok.*;

//房屋转让情况表
@Entity
@NoArgsConstructor
@Data
@Table(name = "bts", indexes = {@Index(columnList = "id")})
public class BuildingTransferSituation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rpdfcid", referencedColumnName = "id")
    RelocationPeopleDwellingFacilityCompensation compensation;

    @Column(name = "name", nullable = false, length = 20)
    String name;

    @Column(name = "baddress", nullable = false, length = 100)
    String buildingAddress;

    @Column(name = "relationship", nullable = false, length = 10)
    String relationship;

    @Column(name = "tcount", nullable = false)
    int transferCount;

    @Column(name = "tarea")
    int transferArea;

    @Column(name = "lcount")
    int leftCount;

    @Column(name = "larea")
    int leftArea;

    public BuildingTransferSituation(String name, String buildingAddress, String relationship, int transferCount, int transferArea, int leftCount, int leftArea) {
        this.compensation = null;
        this.name = name;
        this.buildingAddress = buildingAddress;
        this.relationship = relationship;
        this.transferCount = transferCount;
        this.transferArea = transferArea;
        this.leftCount = leftCount;
        this.leftArea = leftArea;
    }
}
