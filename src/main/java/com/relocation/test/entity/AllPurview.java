package com.relocation.test.entity;

import lombok.*;

import javax.persistence.*;

//所有权限表
@Entity
@NoArgsConstructor
@Data
@Table(name = "apur")
public class AllPurview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //
    @Column(name = "depnum", nullable = false)
    private int purviewNumber;

    @Column(name = "name")
    private String purviewName;

    @Column(name = "des")
    private String purviewDescribe;

    public AllPurview(int purviewNumber, String purviewName, String purviewDescribe) {
        this.purviewNumber = purviewNumber;
        this.purviewName = purviewName;
        this.purviewDescribe = purviewDescribe;
    }
}
