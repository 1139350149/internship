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
    @Column(name = "mdate", nullable = false)
    private Date measureDate;

    //企业名称
    @Column(name = "ename", nullable = false, length = 50)
    private String enterpriseName;

    //企业地址
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

    //征占水井个数
    @Column(name = "well")
    private int wellCount;

    //征占围墙面积
    @Column(name = "wall")
    private double wallArea;

    //征占水泥地面积
    @Column(name = "cement")
    private double cementArea;

    //征占地窖容积
    @Column(name = "cellar")
    private double cellarVolume;

    //动迁人签字
    @Column(name = "rsign", nullable = false, length = 10)
    private String resettleSign;

    //建设办公楼总面积
    @Column(name = "oarea")
    private double totalOfficeArea;

    //建设厂房总面积
    @Column(name = "farea")
    private double totalFactoryArea;

    //建设水泥路总面积
    @Column(name = "crarea")
    private double totalCementRoadArea;

    //建设围墙总面积
    @Column(name = "fearea")
    private double totalFenceArea;


    public EnterpriseTurnoverMeasurement(Date measureDate, String enterpriseName, String address, String occupiedProject, String recorder, String surveyor, int wellCount, double wallArea, double cementArea, double cellarVolume, String resettleSign, double totalOfficeArea, double totalFactoryArea, double totalCementRoadArea, double totalFenceArea) {
        this.measureDate = measureDate;
        this.enterpriseName = enterpriseName;
        this.address = address;
        this.occupiedProject = occupiedProject;
        this.recorder = recorder;
        this.surveyor = surveyor;
        this.wellCount = wellCount;
        this.wallArea = wallArea;
        this.cementArea = cementArea;
        this.cellarVolume = cellarVolume;
        this.resettleSign = resettleSign;
        this.totalOfficeArea = totalOfficeArea;
        this.totalFactoryArea = totalFactoryArea;
        this.totalCementRoadArea = totalCementRoadArea;
        this.totalFenceArea = totalFenceArea;
    }

    public void update(Date measureDate, String enterpriseName, String address, String occupiedProject, String recorder, String surveyor, int wellCount, double wallArea, double cementArea, double cellarVolume, String resettleSign, double totalOfficeArea, double totalFactoryArea, double totalCementRoadArea, double totalFenceArea) {
        this.measureDate = measureDate;
        this.enterpriseName = enterpriseName;
        this.address = address;
        this.occupiedProject = occupiedProject;
        this.recorder = recorder;
        this.surveyor = surveyor;
        this.wellCount = wellCount;
        this.wallArea = wallArea;
        this.cementArea = cementArea;
        this.cellarVolume = cellarVolume;
        this.resettleSign = resettleSign;
        this.totalOfficeArea = totalOfficeArea;
        this.totalFactoryArea = totalFactoryArea;
        this.totalCementRoadArea = totalCementRoadArea;
        this.totalFenceArea = totalFenceArea;
    }
}
