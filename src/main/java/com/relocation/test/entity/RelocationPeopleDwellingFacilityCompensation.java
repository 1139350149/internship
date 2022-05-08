package com.relocation.test.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

//动迁户房屋设施补偿计算表
@Entity
@NoArgsConstructor
@Data
@Table(name = "rpdfc", indexes = {@Index(columnList = "id")})
public class RelocationPeopleDwellingFacilityCompensation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "rpiid", referencedColumnName = "id", nullable = false)
    RelocationPeopleInfo relocationPeopleInfo;

    @Column(name = "mdate", nullable = false)
    Date measureDate;

    @Column(name = "oproject", length = 50, nullable = false)
    String occupationProject;

    @Column(name = "ftvalue")
    double facilityTotalValue;

    @Column(name = "wellcount")
    int wellCount;

    @Column(name = "wellvalue")
    double wellValue;

    @Column(name = "wallarea")
    double wallArea;

    @Column(name = "wallvalue")
    double wallValue;

    @Column(name = "carea")
    double cementArea;

    @Column(name = "cemvalue")
    double cementValue;

    @Column(name = "cvolume")
    double cellarVolume;

    @Column(name = "celvalue")
    double cellarValue;

    @Column(name = "fdate", nullable = false)
    Date fillDate;

    @Column(name = "fillname", nullable = false, length = 20)
    String fillName;

    @Column(name = "remark", length = 200)
    String remark;

    public RelocationPeopleDwellingFacilityCompensation(RelocationPeopleInfo relocationPeopleInfo, Date measureDate
            , String occupationProject, double facilityTotalValue, int wellCount, double wellValue, double wallArea
            , double wallValue, double cementArea, double cementValue, double cellarVolume, double cellarValue
            , Date fillDate, String fillName, String remark) {
        this.relocationPeopleInfo = relocationPeopleInfo;
        this.measureDate = measureDate;
        this.occupationProject = occupationProject;
        this.facilityTotalValue = facilityTotalValue;
        this.wellCount = wellCount;
        this.wellValue = wellValue;
        this.wallArea = wallArea;
        this.wallValue = wallValue;
        this.cementArea = cementArea;
        this.cementValue = cementValue;
        this.cellarVolume = cellarVolume;
        this.cellarValue = cellarValue;
        this.fillDate = fillDate;
        this.fillName = fillName;
        this.remark = remark;
    }

    public void update(Date measureDate
            , String occupationProject, double facilityTotalValue, int wellCount, double wellValue, double wallArea
            , double wallValue, double cementArea, double cementValue, double cellarVolume, double cellarValue
            , Date fillDate, String fillName, String remark){
        this.measureDate = measureDate;
        this.occupationProject = occupationProject;
        this.facilityTotalValue = facilityTotalValue;
        this.wellCount = wellCount;
        this.wellValue = wellValue;
        this.wallArea = wallArea;
        this.wallValue = wallValue;
        this.cementArea = cementArea;
        this.cementValue = cementValue;
        this.cellarVolume = cellarVolume;
        this.cellarValue = cellarValue;
        this.fillDate = fillDate;
        this.fillName = fillName;
        this.remark = remark;
    }
}
