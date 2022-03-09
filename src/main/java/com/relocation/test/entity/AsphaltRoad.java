package com.relocation.test.entity;

import javax.persistence.*;

import lombok.*;

//沥青路表
@Entity
@NoArgsConstructor
@Data
@Table(name = "ear", indexes = {@Index(columnList = "id")})

public class AsphaltRoad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //外键测量表ID 手工输入
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "etmid", referencedColumnName = "id", unique = true)
    EnterpriseTurnoverMeasurement enterpriseTurnoverMeasurement;

    //总平数 手工输入
    @Column(name = "tarea", nullable = false)
    private int totalArea;

    public AsphaltRoad(int totalArea) {
        this.totalArea = totalArea;
    }
}
