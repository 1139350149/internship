package com.relocation.test.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.sql.Date;


//企业动迁测量表
@Entity
@NoArgsConstructor
@Data
@Table(name = "etm")
public class EnterpriseTurnoverMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //测量日期
    @Column(name = "date", nullable = false)
    private Date Date;

    //测量表序号
    @Column(name = "mno", nullable = false, length = 10)
    private String measureNo;

    //企业名称
    @Column(name = "ename", nullable = false, length = 50)
    private String enterpriseName;

    //地址
    @Column(name = "address", nullable = false, length = 100)
    private String address;

    //征占项目
    @Column(name = "oproject", nullable = false, length = 20)
    private String occupiedProject;

    //记录人
    @Column(name = "recorder", nullable = false, length = 10)
    private String recorder;

    //测量人员
    @Column(name = "surveyor", nullable = false, length = 50)
    private String surveyor;

    //棚厦
    @Column(name = "shed")
    private int shed;

    //水井
    @Column(name = "well")
    private int well;

    //院门
    @Column(name = "door")
    private int door;

    //电话
    @Column(name = "phone")
    private int phone;

    //有线电视
    @Column(name = "ctv")
    private int cableTV;

    //其他
    @Column(name = "others", length = 100)
    private String others;

    //动迁人签字
    @Column(name = "rsign", nullable = false, length = 10)
    private String resettleSign;

    //被动迁人签字
    @Column(name = "bssign", nullable = false, length = 10)
    private String beSettledSign;

    //办公楼外键
    @OneToOne(mappedBy = "enterpriseTurnoverMeasurement")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"officeBuilding"})
    OfficeBuilding officeBuilding;

    //厂房表外键
    @OneToOne(mappedBy = "enterpriseTurnoverMeasurement")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"factory"})
    Factory factory;

    //小房表外键
    @OneToOne(mappedBy = "enterpriseTurnoverMeasurement")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"enterpriseSmallBuilding"})
    EnterpriseSmallBuilding enterpriseSmallBuilding;

    //水泥地表外键
    @OneToOne(mappedBy = "enterpriseTurnoverMeasurement")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"cementGround"})
    CementGround cementGround;

    //砖地表外键
    @OneToOne(mappedBy = "enterpriseTurnoverMeasurement")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"brickGround"})
    BrickGround brickGround;

    //水泥路表外键
    @OneToOne(mappedBy = "enterpriseTurnoverMeasurement")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"cementRoad"})
    CementRoad cementRoad;

    //沥青路表外键
    @OneToOne(mappedBy = "enterpriseTurnoverMeasurement")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"asphaltRoad"})
    AsphaltRoad asphaltRoad;

    //护坡墙表外键
    @OneToOne(mappedBy = "enterpriseTurnoverMeasurement")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"slopeWall"})
    SlopeWall slopeWall;

    //挡土墙表外键
    @OneToOne(mappedBy = "enterpriseTurnoverMeasurement")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"retainingWall"})
    RetainingWall retainingWall;

    //围墙表外键
    @OneToOne(mappedBy = "enterpriseTurnoverMeasurement")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"fences"})
    Fences fences;

    public EnterpriseTurnoverMeasurement(Date date, String measureNo, String enterpriseName, String address, String occupiedProject, String recorder, String surveyor, int shed, int well, int door, int phone, int cableTV, String others, String resettleSign, String beSettledSign) {
        Date = date;
        this.measureNo = measureNo;
        this.enterpriseName = enterpriseName;
        this.address = address;
        this.occupiedProject = occupiedProject;
        this.recorder = recorder;
        this.surveyor = surveyor;
        this.shed = shed;
        this.well = well;
        this.door = door;
        this.phone = phone;
        this.cableTV = cableTV;
        this.others = others;
        this.resettleSign = resettleSign;
        this.beSettledSign = beSettledSign;
    }
}
