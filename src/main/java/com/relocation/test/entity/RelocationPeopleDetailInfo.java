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
    @JoinColumn(name = "infoid")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"relocationPeopleInfo"})
    private RelocationPeopleInfo relocationPeopleInfo;

    @Column(name = "name", nullable = false, length = 10)
    String name;

    @Column(name = "relation", nullable = false, length = 10)
    String relation;

    @Column(name = "gender", nullable = false, length = 4)
    String gender;

    @Column(name = "notonly")        //false = yes, true = no
    boolean notOnly;

    @Column(name = "birth")
    Date birth;

    @Column(name = "rpslps", nullable = false, length = 50)
    String bookOfRegisteredPermanentResidencePoliceStation;

    @Column(name = "rpsid", nullable = false, length = 20)
    String bookOfRegisteredPermanentResidenceId;

    @Column(name = "rpsregdate", nullable = false)
    String bookOfRegisteredPermanentResidenceRegTime;

    @Column(name = "mcoffice", length = 50)
    String marriageCertificateOffice;

    @Column(name = "mcid", nullable = false)
    String marriageCertificateId;

    @Column(name = "mcregdate", nullable = false)
    String marriageCertificateRegisterDate;

    @Column(name = "remark", length = 50, nullable = false)
    String remark;

    public RelocationPeopleDetailInfo(String name, String relation, String gender, boolean notOnly, Date birth, String bookOfRegisteredPermanentResidencePoliceStation, String bookOfRegisteredPermanentResidenceId, String bookOfRegisteredPermanentResidenceRegTime, String marriageCertificateOffice, String marriageCertificateId, String marriageCertificateRegisterDate, String remark) {
        this.name = name;
        this.relation = relation;
        this.gender = gender;
        this.notOnly = notOnly;
        this.birth = birth;
        this.bookOfRegisteredPermanentResidencePoliceStation = bookOfRegisteredPermanentResidencePoliceStation;
        this.bookOfRegisteredPermanentResidenceId = bookOfRegisteredPermanentResidenceId;
        this.bookOfRegisteredPermanentResidenceRegTime = bookOfRegisteredPermanentResidenceRegTime;
        this.marriageCertificateOffice = marriageCertificateOffice;
        this.marriageCertificateId = marriageCertificateId;
        this.marriageCertificateRegisterDate = marriageCertificateRegisterDate;
        this.remark = remark;
    }
}
