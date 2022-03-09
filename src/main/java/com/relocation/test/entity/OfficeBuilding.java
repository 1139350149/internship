package com.relocation.test.entity;

import javax.persistence.*;

import lombok.*;

//办公楼表
@Entity
@NoArgsConstructor
@Data
@Table(name = "eob", indexes = {@Index(columnList = "id")})
public class OfficeBuilding {
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

    //等级 手工输入
    @Column(name = "level", nullable = false , length = 10)
    private String level;

    public OfficeBuilding(int totalArea, String level) {
        this.totalArea = totalArea;
        this.level = level;
    }
}
