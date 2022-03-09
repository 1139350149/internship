package com.relocation.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

//动迁户房屋设施补偿计算表
@Entity
@NoArgsConstructor
@Data
@Table(name = "rpdfc ", indexes = {@Index(columnList = "id")})
public class RelocationPeopleDwellingFaciltyCompensation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rpiid", referencedColumnName = "id")
    RelocationPeopleInfo relocationPeopleInfo;

    @OneToOne(mappedBy = "compensation")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"buildingTransferSituation"})
    BuildingTransferSituation buildingTransferSituation;

    @OneToMany(mappedBy = "compensation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"mainBuildings"})
    private List<MainBuilding> mainBuildings;

    @OneToMany(mappedBy = "compensation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"smallBuildings"})
    private List<SmallBuilding> smallBuildings;

    @OneToMany(mappedBy = "compensation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"specialFruiters"})
    private List<SpecialFruiter> specialFruiters;

    @OneToMany(mappedBy = "compensation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"tree1_8s"})
    private List<Tree1_8> tree1_8s;

    @OneToMany(mappedBy = "compensation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"tree1_7s"})
    private List<Tree1_7> tree1_7s;

    @OneToMany(mappedBy = "compensation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"yardFacilities"})
    private List<YardFacility> yardFacilities;

    @Column(name = "ownername", length = 20, nullable = false)
    String ownerName;

    @Column(name = "address", length = 100)
    String address;

    @Column(name = "mdate", nullable = false)
    Date measureDate;

    @Column(name = "oproject", length = 50, nullable = false)
    String occupationProject;

    @Column(name = "rhtype", length = 50, nullable = false)
    String relocationHousingType;

    @Column(name = "shnumber", length = 20, nullable = false)
    String settlementHousingNumber;

    @Column(name = "ftvalue")
    int facilityTotalValue;

    @Column(name = "wellcount")
    int wellCount;

    @Column(name = "welluprice")
    int wellUnitPrice;

    @Column(name = "wellvalue")
    int wellValue;

    @Column(name = "bdcount")
    int biogasDigesterCount;

    @Column(name = "bduuprice")
    int biogasDigesterUnitPrice;

    @Column(name = "bdValue")
    int biogasDigesterValue;

    @Column(name = "scount")
    int styCount;

    @Column(name = "suprice")
    int styUnitPrice;

    @Column(name = "svalue")
    int styValue;

    @Column(name = "toicount")
    int toiletCount;

    @Column(name = "tuprice")
    int toiletUnitPrice;

    @Column(name = "toivalue")
    int toiletValue;

    @Column(name = "ttcount")
    int telephoneTransferCount;

    @Column(name = "ttuprice")
    int telephoneTransferUnitPrice;

    @Column(name = "ttvalue")
    int telephoneTransferValue;

    @Column(name = "gnumber")
    int gateNumber;

    @Column(name = "guprice")
    int gateUnitPrice;

    @Column(name = "gvalue")
    int gateValue;

    @Column(name = "csarea")
    int corralShedArea;

    @Column(name = "csuprice")
    int corralShedUnitPrice;

    @Column(name = "csvalue")
    int corralShedValue;

    @Column(name = "wallarea")
    int wallArea;

    @Column(name = "walluprice")
    int wallUnitPrice;

    @Column(name = "wallvalue")
    int wallValue;

    @Column(name = "twcount")
    int tapWaterCount;

    @Column(name = "twuprice")
    int tapWaterUnitPrice;

    @Column(name = "twvalue")
    int tapWaterValue;

    @Column(name = "carea")
    int cementArea;

    @Column(name = "cemuprice")
    int cementUnitPrice;

    @Column(name = "cemvalue")
    int cementValue;

    @Column(name = "ctvcount")
    int cableTVCount;

    @Column(name = "ctvUnitPrice")
    int cableTVUnitPrice;

    @Column(name = "ctvtvalue")
    int cableTVTransferValue;

    @Column(name = "cvolume")
    int cellarVolume;

    @Column(name = "celuprice")
    int cellarUnitPrice;

    @Column(name = "celvalue")
    int cellarValue;

    @Column(name = "tname")
    int treeName;

    @Column(name = "treecount")
    int treeCount;

    @Column(name = "tppyear")
    int treePricePerYear;

    @Column(name = "treevalue")
    int treeValue;

    @Column(name = "fname")
    int fruiterName;

    @Column(name = "fcount")
    int fruiterCount;

    @Column(name = "fppyear")
    int fruiterPricePerYear;

    @Column(name = "fvalue")
    int fruiterValue;

    @Column(name = "fdate", nullable = false)
    Date fillDate;

    @Column(name = "fillname", nullable = false, length = 20)
    String fillName;

    @Column(name = "remark", length = 200)
    String remark;

    public RelocationPeopleDwellingFaciltyCompensation(String ownerName, String address, Date measureDate, String occupationProject, String relocationHousingType, String settlementHousingNumber, int facilityTotalValue, int wellCount, int wellUnitPrice, int wellValue, int biogasDigesterCount, int biogasDigesterUnitPrice, int biogasDigesterValue, int styCount, int styUnitPrice, int styValue, int toiletCount, int toiletUnitPrice, int toiletValue, int telephoneTransferCount, int telephoneTransferUnitPrice, int telephoneTransferValue, int gateNumber, int gateUnitPrice, int gateValue, int corralShedArea, int corralShedUnitPrice, int corralShedValue, int wallArea, int wallUnitPrice, int wallValue, int tapWaterCount, int tapWaterUnitPrice, int tapWaterValue, int cementArea, int cementUnitPrice, int cementValue, int cableTVCount, int cableTVUnitPrice, int cableTVTransferValue, int cellarVolume, int cellarUnitPrice, int cellarValue, int treeName, int treeCount, int treePricePerYear, int treeValue, int fruiterName, int fruiterCount, int fruiterPricePerYear, int fruiterValue, Date fillDate, String fillName, String remark) {
        mainBuildings = new ArrayList<>();
        smallBuildings = new ArrayList<>();
        specialFruiters = new ArrayList<>();
        tree1_7s = new ArrayList<>();
        tree1_8s = new ArrayList<>();
        yardFacilities = new ArrayList<>();
        this.ownerName = ownerName;
        this.address = address;
        this.measureDate = measureDate;
        this.occupationProject = occupationProject;
        this.relocationHousingType = relocationHousingType;
        this.settlementHousingNumber = settlementHousingNumber;
        this.facilityTotalValue = facilityTotalValue;
        this.wellCount = wellCount;
        this.wellUnitPrice = wellUnitPrice;
        this.wellValue = wellValue;
        this.biogasDigesterCount = biogasDigesterCount;
        this.biogasDigesterUnitPrice = biogasDigesterUnitPrice;
        this.biogasDigesterValue = biogasDigesterValue;
        this.styCount = styCount;
        this.styUnitPrice = styUnitPrice;
        this.styValue = styValue;
        this.toiletCount = toiletCount;
        this.toiletUnitPrice = toiletUnitPrice;
        this.toiletValue = toiletValue;
        this.telephoneTransferCount = telephoneTransferCount;
        this.telephoneTransferUnitPrice = telephoneTransferUnitPrice;
        this.telephoneTransferValue = telephoneTransferValue;
        this.gateNumber = gateNumber;
        this.gateUnitPrice = gateUnitPrice;
        this.gateValue = gateValue;
        this.corralShedArea = corralShedArea;
        this.corralShedUnitPrice = corralShedUnitPrice;
        this.corralShedValue = corralShedValue;
        this.wallArea = wallArea;
        this.wallUnitPrice = wallUnitPrice;
        this.wallValue = wallValue;
        this.tapWaterCount = tapWaterCount;
        this.tapWaterUnitPrice = tapWaterUnitPrice;
        this.tapWaterValue = tapWaterValue;
        this.cementArea = cementArea;
        this.cementUnitPrice = cementUnitPrice;
        this.cementValue = cementValue;
        this.cableTVCount = cableTVCount;
        this.cableTVUnitPrice = cableTVUnitPrice;
        this.cableTVTransferValue = cableTVTransferValue;
        this.cellarVolume = cellarVolume;
        this.cellarUnitPrice = cellarUnitPrice;
        this.cellarValue = cellarValue;
        this.treeName = treeName;
        this.treeCount = treeCount;
        this.treePricePerYear = treePricePerYear;
        this.treeValue = treeValue;
        this.fruiterName = fruiterName;
        this.fruiterCount = fruiterCount;
        this.fruiterPricePerYear = fruiterPricePerYear;
        this.fruiterValue = fruiterValue;
        this.fillDate = fillDate;
        this.fillName = fillName;
        this.remark = remark;
    }
}
