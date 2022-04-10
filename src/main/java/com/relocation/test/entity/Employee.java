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
    private int num;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "pwd", nullable = false, length = 20)
    private String pwd;

    @Column(name = "email", nullable = false, length = 20)
    private String email;

    @JsonIgnoreProperties({"department"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depnum")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Department department;

    @Column(name = "gender", nullable = false)
    private boolean gender;        // false = female, true = male

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "remark")
    private String remark;

    public Employee(int num, String name, String pwd, String email, Department department, boolean gender, String phone, String remark) {
        this.num = num;
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.department = department;
        this.gender = gender;
        this.phone = phone;
        this.remark = remark;
    }

    public boolean getGender(){
        return this.gender;
    }

    public void setGender(boolean g){
        this.gender = g;
    }
}
