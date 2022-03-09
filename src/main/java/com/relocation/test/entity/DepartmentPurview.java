package com.relocation.test.entity;

import lombok.*;

import javax.persistence.*;

//部门权限表
@Entity
@NoArgsConstructor
@Data
@Table(name = "deppur")
public class DepartmentPurview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "purnum", nullable = false)
    private int purNumber;

    @Column(name = "depnum", nullable = false)
    private int depNumber;

    @Column(name = "des")
    private String depDescribe;

    public DepartmentPurview(int purNumber, int depNumber, String depDescribe) {
        this.purNumber = purNumber;
        this.depNumber = depNumber;
        this.depDescribe = depDescribe;
    }
}
