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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rpiid", referencedColumnName = "id")
    RelocationPeopleInfo relocationPeopleInfo;

    @Column(name = "ownername", length = 20, nullable = false)
    String ownerName;

    @Column(name = "population", length = 10, nullable = false)
    String population;

    @Column(name = "oaddress", length = 100)
    String originalAddress;

    @Column(name = "dbtype", length = 20, nullable = false)
    String distributedBuildingType;

    @Column(name = "bnumber", length = 20, nullable = false)
    String buildingNumber;

    @Column(name = "obccount", nullable = false)
    int originalBuildingChamberCount;

    @Column(name = "obarea", nullable = false)
    int originalBuildingArea;

    @Column(name = "oblevel", length = 20)
    String originalBuildingLevel;

    @Column(name = "obup")
    int originalBuildingUnitPrice;

    @Column(name = "obv")
    int originalBuildingValue;

    @Column (name = "osbccount")
    int originalSubBuildingChamberCount;

    @Column(name = "osbarea")
    int originalSubBuildingArea;

    @Column(name = "osblevel", length = 20)
    String originalSubBuildingLevel;

    @Column(name = "osbuprice", nullable = false)
    int originalSubBuildingUnitPrice;

    @Column(name = "osbvalue", nullable = false)
    int originalSubBuildingValue;

    @Column(name = "obfvalue", nullable = false)
    int originalBuildingFacilitiesValue;

    @Column(name = "dbccount", nullable = false)
    int distributedBuildingChamberCount;

    @Column(name = "dbarea", nullable = false)
    int distributedBuildingArea;

    @Column(name = "dbpipercentage")
    int distributedBuildingPriceIncreasedPercentage;

    @Column(name = "dbpdpercentage")
    int distributedBuildingPriceDecreasePercentage;

    @Column(name = "dbaarea")
    int distributedBuildingAllocatedArea;

    @Column(name = "dbauprice")
    int distributedBuildingAllocatedUnitPrice;

    @Column(name = "dbavalue")
    int distributedBuildingAllocatedValue;

    @Column(name = "dbaincrease")
    int distributedBuildingAllocatedIncrease;

    @Column(name = "dbadecrease")
    int distributedBuildingAllocatedDecrease;

    @Column(name = "dbasdarea")
    int distributedBuildingAllocatedShallDivideArea;

    @Column(name = "dbasduprice")
    int distributedBuildingAllocatedShallDivideUnitPrice;

    @Column(name = "dbasdvalue")
    int distributedBuildingAllocatedShallDivideValue;

    @Column(name = "dbasdincrease")
    int distributedBuildingAllocatedShallDivideIncrease;

    @Column(name = "dbasddecrease")
    int distributedBuildingAllocatedShallDivideDecrease;

    @Column(name = "dbatiarea")
    int distributedBuildingAllocatedToIncreaseArea;

    @Column(name = "dbatiuprice")
    int distributedBuildingAllocatedToIncreaseUnitPrice;

    @Column(name = "dbativalue")
    int distributedBuildingAllocatedToIncreaseValue;

    @Column(name = "dbatiincrease")
    int distributedBuildingAllocatedToIncreaseIncrease;

    @Column(name = "dbatidecrease")
    int distributedBuildingAllocatedToIncreaseDecrease;

    @Column(name = "dbaolarea")
    int distributedBuildingAllocatedOverLimitArea;

    @Column(name = "dbaoluprice")
    int distributedBuildingAllocatedOverLimitUnitPrice;

    @Column(name = "dbaolvalue")
    int distributedBuildingAllocatedOverLimitValue;

    @Column(name = "dbaolincrease")
    int distributedBuildingAllocatedOverLimitIncrease;

    @Column(name = "dbaoldecrease")
    int distributedBuildingAllocatedOverLimitDecrease;

    @Column(name = "dbaoarea")
    int distributedBuildingAllocatedOtherArea;

    @Column(name = "dbaouprice")
    int distributedBuildingAllocatedOtherUnitPrice;

    @Column(name = "dbaovalue")
    int distributedBuildingAllocatedOtherValue;

    @Column(name = "dbaoincrease")
    int distributedBuildingAllocatedOtherIncrease;

    @Column(name = "dbaodecrease")
    int distributedBuildingAllocatedOtherDecrease;

    @Column(name = "dbatvalue")
    int distributedBuildingAllocatedTotalValue;

    @Column(name = "dbaaobsamount")
    int distributedBuildingAllocatedAndOriginalBuildingSettlementAmount;

    @Column(name = "dbaaobsamountstring")
    String distributedBuildingAllocatedAndOriginalBuildingSettlementAmountString;

    @Column(name = "remark", length = 200)
    String remark;

    @Column(name = "officer", length = 10, nullable = false)
    String officer;

    @Column(name = "settlement", length = 10, nullable = false)
    String settlement;

    @Column(name = "creview", length = 10, nullable = false)
    String creditReview;

    @Column(name = "sdate", nullable = false)
    Date settlementDate;

    @Column(name = "fdate", nullable = false)
    Date fillDate;

    @Column(name = "fname", length = 10, nullable = false)
    String fillName;

    public DistributionOfBuildingExpensesSettlement(String ownerName, String population, String originalAddress,
                                                    String distributedBuildingType, String buildingNumber,
                                                    int originalBuildingChamberCount, int originalBuildingArea,
                                                    String originalBuildingLevel, int originalBuildingUnitPrice,
                                                    int originalBuildingValue, int originalSubBuildingChamberCount,
                                                    int originalSubBuildingArea, String originalSubBuildingLevel,
                                                    int originalSubBuildingUnitPrice, int originalSubBuildingValue,
                                                    int originalBuildingFacilitiesValue,
                                                    int distributedBuildingChamberCount, int distributedBuildingArea,
                                                    int distributedBuildingPriceIncreasedPercentage,
                                                    int distributedBuildingPriceDecreasePercentage,
                                                    int distributedBuildingAllocatedArea,
                                                    int distributedBuildingAllocatedUnitPrice,
                                                    int distributedBuildingAllocatedValue,
                                                    int distributedBuildingAllocatedIncrease,
                                                    int distributedBuildingAllocatedDecrease,
                                                    int distributedBuildingAllocatedShallDivideArea,
                                                    int distributedBuildingAllocatedShallDivideUnitPrice,
                                                    int distributedBuildingAllocatedShallDivideValue,
                                                    int distributedBuildingAllocatedShallDivideIncrease,
                                                    int distributedBuildingAllocatedShallDivideDecrease,
                                                    int distributedBuildingAllocatedToIncreaseArea,
                                                    int distributedBuildingAllocatedToIncreaseUnitPrice,
                                                    int distributedBuildingAllocatedToIncreaseValue,
                                                    int distributedBuildingAllocatedToIncreaseIncrease,
                                                    int distributedBuildingAllocatedToIncreaseDecrease,
                                                    int distributedBuildingAllocatedOverLimitArea,
                                                    int distributedBuildingAllocatedOverLimitUnitPrice,
                                                    int distributedBuildingAllocatedOverLimitValue,
                                                    int distributedBuildingAllocatedOverLimitIncrease,
                                                    int distributedBuildingAllocatedOverLimitDecrease,
                                                    int distributedBuildingAllocatedOtherArea,
                                                    int distributedBuildingAllocatedOtherUnitPrice,
                                                    int distributedBuildingAllocatedOtherValue,
                                                    int distributedBuildingAllocatedOtherIncrease,
                                                    int distributedBuildingAllocatedOtherDecrease,
                                                    int distributedBuildingAllocatedTotalValue,
                                                    int distributedBuildingAllocatedAndOriginalBuildingSettlementAmount,
                                                    String distributedBuildingAllocatedAndOriginalBuildingSettlementAmountString,
                                                    String remark, String officer, String settlement,
                                                    String creditReview, Date settlementDate, Date fillDate,
                                                    String fillName) {
        this.ownerName = ownerName;
        this.population = population;
        this.originalAddress = originalAddress;
        this.distributedBuildingType = distributedBuildingType;
        this.buildingNumber = buildingNumber;
        this.originalBuildingChamberCount = originalBuildingChamberCount;
        this.originalBuildingArea = originalBuildingArea;
        this.originalBuildingLevel = originalBuildingLevel;
        this.originalBuildingUnitPrice = originalBuildingUnitPrice;
        this.originalBuildingValue = originalBuildingValue;
        this.originalSubBuildingChamberCount = originalSubBuildingChamberCount;
        this.originalSubBuildingArea = originalSubBuildingArea;
        this.originalSubBuildingLevel = originalSubBuildingLevel;
        this.originalSubBuildingUnitPrice = originalSubBuildingUnitPrice;
        this.originalSubBuildingValue = originalSubBuildingValue;
        this.originalBuildingFacilitiesValue = originalBuildingFacilitiesValue;
        this.distributedBuildingChamberCount = distributedBuildingChamberCount;
        this.distributedBuildingArea = distributedBuildingArea;
        this.distributedBuildingPriceIncreasedPercentage = distributedBuildingPriceIncreasedPercentage;
        this.distributedBuildingPriceDecreasePercentage = distributedBuildingPriceDecreasePercentage;
        this.distributedBuildingAllocatedArea = distributedBuildingAllocatedArea;
        this.distributedBuildingAllocatedUnitPrice = distributedBuildingAllocatedUnitPrice;
        this.distributedBuildingAllocatedValue = distributedBuildingAllocatedValue;
        this.distributedBuildingAllocatedIncrease = distributedBuildingAllocatedIncrease;
        this.distributedBuildingAllocatedDecrease = distributedBuildingAllocatedDecrease;
        this.distributedBuildingAllocatedShallDivideArea = distributedBuildingAllocatedShallDivideArea;
        this.distributedBuildingAllocatedShallDivideUnitPrice = distributedBuildingAllocatedShallDivideUnitPrice;
        this.distributedBuildingAllocatedShallDivideValue = distributedBuildingAllocatedShallDivideValue;
        this.distributedBuildingAllocatedShallDivideIncrease = distributedBuildingAllocatedShallDivideIncrease;
        this.distributedBuildingAllocatedShallDivideDecrease = distributedBuildingAllocatedShallDivideDecrease;
        this.distributedBuildingAllocatedToIncreaseArea = distributedBuildingAllocatedToIncreaseArea;
        this.distributedBuildingAllocatedToIncreaseUnitPrice = distributedBuildingAllocatedToIncreaseUnitPrice;
        this.distributedBuildingAllocatedToIncreaseValue = distributedBuildingAllocatedToIncreaseValue;
        this.distributedBuildingAllocatedToIncreaseIncrease = distributedBuildingAllocatedToIncreaseIncrease;
        this.distributedBuildingAllocatedToIncreaseDecrease = distributedBuildingAllocatedToIncreaseDecrease;
        this.distributedBuildingAllocatedOverLimitArea = distributedBuildingAllocatedOverLimitArea;
        this.distributedBuildingAllocatedOverLimitUnitPrice = distributedBuildingAllocatedOverLimitUnitPrice;
        this.distributedBuildingAllocatedOverLimitValue = distributedBuildingAllocatedOverLimitValue;
        this.distributedBuildingAllocatedOverLimitIncrease = distributedBuildingAllocatedOverLimitIncrease;
        this.distributedBuildingAllocatedOverLimitDecrease = distributedBuildingAllocatedOverLimitDecrease;
        this.distributedBuildingAllocatedOtherArea = distributedBuildingAllocatedOtherArea;
        this.distributedBuildingAllocatedOtherUnitPrice = distributedBuildingAllocatedOtherUnitPrice;
        this.distributedBuildingAllocatedOtherValue = distributedBuildingAllocatedOtherValue;
        this.distributedBuildingAllocatedOtherIncrease = distributedBuildingAllocatedOtherIncrease;
        this.distributedBuildingAllocatedOtherDecrease = distributedBuildingAllocatedOtherDecrease;
        this.distributedBuildingAllocatedTotalValue = distributedBuildingAllocatedTotalValue;
        this.distributedBuildingAllocatedAndOriginalBuildingSettlementAmount = distributedBuildingAllocatedAndOriginalBuildingSettlementAmount;
        this.distributedBuildingAllocatedAndOriginalBuildingSettlementAmountString = distributedBuildingAllocatedAndOriginalBuildingSettlementAmountString;
        this.remark = remark;
        this.officer = officer;
        this.settlement = settlement;
        this.creditReview = creditReview;
        this.settlementDate = settlementDate;
        this.fillDate = fillDate;
        this.fillName = fillName;
    }
}
