package com.relocation.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

//动迁人口登记明细表
@Entity
@NoArgsConstructor
@Data
@Table(name = "ppldetail", indexes = {@Index(columnList = "id")})
public class RelocationPeopleDetailInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "headid")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"relocationPeopleInfo"})
    private RelocationPeopleInfo relocationPeopleInfo;

    @Column(name = "name", nullable = false, length = 10)
    String name;

    @Column(name = "idcard", length = 18)
    String idCard;

    @Column(name = "relation", nullable = false, length = 10)
    String relation;

    @Column(name = "birth")
    Date birth;

    @Column(name = "remark", length = 50, nullable = false)
    String remark;


    public RelocationPeopleDetailInfo(String name, String idCard, Date birth, String relation,
                                      String remark, RelocationPeopleInfo relocationPeopleInfo) {
        this.relocationPeopleInfo = relocationPeopleInfo;
        this.name = name;
        this.relation = relation;
        this.birth = birth;
        this.remark = remark;
        this.idCard = idCard;
    }
}
