package com.relocation.test.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

//动迁人口登记表
@Entity
@NoArgsConstructor
@Data
@Table(name = "pplinfo", indexes = {@Index(columnList = "id")})
public class RelocationPeopleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "hl", nullable = false, length = 20)
    String owner;

    @Column(name = "address", nullable = false, length = 100)
    String address;

    @Column(name = "regdate", nullable = false)
    Date registerDate;

    @Column(name = "remark")
    String remark;

    @Column(name = "sign", nullable = false, length = 20)
    String signature;

    @Column(name = "prin", nullable = false, length = 10)
    String principal;

    @Column(name = "resp", nullable = false, length = 10)
    String responsiblePeople;

    @Column(name = "filldate")
    Date fillDate;

    @OneToMany(mappedBy = "relocationPeopleInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"relocationPeopleDetailInfos"})
    List<RelocationPeopleDetailInfo> relocationPeopleDetailInfos;

    @OneToOne(mappedBy = "relocationPeopleInfo")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"settlement"})
    DistributionOfBuildingExpensesSettlement settlement;

    @OneToOne(mappedBy = "relocationPeopleInfo")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"compensation"})
    RelocationPeopleDwellingFaciltyCompensation compensation;

    public RelocationPeopleInfo(String owner, String address, Date registerDate, String remark, String signature, String principal, String responsiblePeople, Date fillDate) {
        this.owner = owner;
        this.address = address;
        this.registerDate = registerDate;
        this.remark = remark;
        this.signature = signature;
        this.principal = principal;
        this.responsiblePeople = responsiblePeople;
        this.fillDate = fillDate;
        relocationPeopleDetailInfos = new ArrayList<>();
    }
}
