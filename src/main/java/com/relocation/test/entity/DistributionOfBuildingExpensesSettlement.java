package com.relocation.test.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

//动迁户分配楼房费用结算表
@Entity
@NoArgsConstructor
@Data
@Table(name = "dobes", indexes = {@Index(columnList = "id")})
public class DistributionOfBuildingExpensesSettlement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "rpiid", referencedColumnName = "id", nullable = false)
    RelocationPeopleInfo relocationPeopleInfo;

    @Column(name = "oaddress", length = 100)
    String originalAddress;

    @Column(name = "obarea")
    double originalBuildingArea;

    @Column(name = "obv")
    double originalBuildingValue;

    @Column(name = "obfvalue")
    double originalBuildingFacilitiesValue;

    @Column(name = "dbatarea")
    double distributedBuildingAllocatedTotalArea;

    @Column(name = "dbatvalue")
    double distributedBuildingAllocatedTotalValue;

    @Column(name = "dbaaobsamount")
    double distributedBuildingAllocatedAndOriginalBuildingSettlementAmount;

    @Column(name = "remark", length = 200)
    String remark;

    @Column(name = "officer", length = 10)
    String officer;

    @Column(name = "settlement", length = 10)
    double settlement;

    @Column(name = "ispledge")
    boolean isPledge;

    @Column(name = "ppercentage")
    double pledgePercentage;

    @Column(name = "cview", length = 10)
    boolean creditReview;

    @Column(name = "sdate")
    Date settlementDate;

    @Column(name = "fdate", nullable = false)
    Date fillDate;

    @Column(name = "fname", length = 10)
    String fillName;

    public DistributionOfBuildingExpensesSettlement(RelocationPeopleInfo relocationPeopleInfo, String originalAddress
            , double originalBuildingArea, double originalBuildingValue, double originalBuildingFacilitiesValue
            , double distributedBuildingAllocatedTotalArea, double distributedBuildingAllocatedTotalValue
            , double distributedBuildingAllocatedAndOriginalBuildingSettlementAmount, String remark, String officer
            , double settlement, boolean creditReview, Date settlementDate, Date fillDate, String fillName) {
        this.relocationPeopleInfo = relocationPeopleInfo;
        this.originalAddress = originalAddress;
        this.originalBuildingArea = originalBuildingArea;
        this.originalBuildingValue = originalBuildingValue;
        this.originalBuildingFacilitiesValue = originalBuildingFacilitiesValue;
        this.distributedBuildingAllocatedTotalArea = distributedBuildingAllocatedTotalArea;
        this.distributedBuildingAllocatedTotalValue = distributedBuildingAllocatedTotalValue;
        this.distributedBuildingAllocatedAndOriginalBuildingSettlementAmount = distributedBuildingAllocatedAndOriginalBuildingSettlementAmount;
        this.remark = remark;
        this.officer = officer;
        this.settlement = settlement;
        this.creditReview = creditReview;
        this.settlementDate = settlementDate;
        this.fillDate = fillDate;
        this.fillName = fillName;
        this.isPledge = false;
        this.pledgePercentage = 0.1;
    }

    public void update(String originalAddress
            , double originalBuildingArea, double originalBuildingValue, double originalBuildingFacilitiesValue
            , double distributedBuildingAllocatedTotalArea, double distributedBuildingAllocatedTotalValue
            , double distributedBuildingAllocatedAndOriginalBuildingSettlementAmount, String remark, String officer
            , double settlement, Date fillDate, String fillName, double ppercentage) {
        this.originalAddress = originalAddress;
        this.originalBuildingArea = originalBuildingArea;
        this.originalBuildingValue = originalBuildingValue;
        this.originalBuildingFacilitiesValue = originalBuildingFacilitiesValue;
        this.distributedBuildingAllocatedTotalArea = distributedBuildingAllocatedTotalArea;
        this.distributedBuildingAllocatedTotalValue = distributedBuildingAllocatedTotalValue;
        this.distributedBuildingAllocatedAndOriginalBuildingSettlementAmount = distributedBuildingAllocatedAndOriginalBuildingSettlementAmount;
        this.remark = remark;
        this.officer = officer;
        this.settlement = settlement;
        this.fillDate = fillDate;
        this.fillName = fillName;
        this.pledgePercentage = ppercentage;
    }
}
