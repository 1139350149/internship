package com.relocation.test.entity;

import lombok.*;

import javax.persistence.*;

//员工权限表
@Entity
@NoArgsConstructor
@Data
@Table(name = "emppur")
public class EmployeePurview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "purnum", nullable = false)
    private int purNumber;

    @Column(name = "empnum", nullable = false)
    private int depNumber;

    @Column(name = "des")
    private String depDescribe;

    public EmployeePurview(int purNumber, int depNumber, String depDescribe) {
        this.purNumber = purNumber;
        this.depNumber = depNumber;
        this.depDescribe = depDescribe;
    }
}
