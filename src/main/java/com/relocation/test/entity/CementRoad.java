package com.relocation.test.entity;

import javax.persistence.*;

import lombok.*;

//水泥路表
@Entity
@NoArgsConstructor
@Data
@Table(name = "ecr", indexes = {@Index(columnList = "id")})

public class CementRoad {
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

    public CementRoad(int totalArea) {
        this.totalArea = totalArea;
    }
}
