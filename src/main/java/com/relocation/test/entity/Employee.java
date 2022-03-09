package com.relocation.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

//员工表
@Entity
@NoArgsConstructor
@Data
@Table(name = "emp")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "enum", nullable = false)
    private int eNum;

    @Column(name = "name", nullable = false, length = 50)
    private String eName;

    @Column(name = "pwd", nullable = false, length = 20)
    private String ePwd;

    @Column(name = "email", nullable = false, length = 20)
    private String eEmail;

    @JsonIgnoreProperties({"department"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depnum")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Department department;

    @Column(name = "gender", nullable = false)
    private boolean eGender;        // false = female, true = male

    @Column(name = "phone", length = 50)
    private String ePhone;

    @Column(name = "remark")
    private String remark;

    public Employee(int eNum, String eName, String ePwd, String eEmail, Department department, boolean eGender, String ePhone, String remark) {
        this.eNum = eNum;
        this.eName = eName;
        this.ePwd = ePwd;
        this.eEmail = eEmail;
        this.department = department;
        this.eGender = eGender;
        this.ePhone = ePhone;
        this.remark = remark;
    }
}
