package com.relocation.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.*;

//部门表
@Entity
@NoArgsConstructor
@Data
@Table(name = "dep")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "depnum", nullable = false)
    private int depNumber;

    @Column(name = "name", length = 10)
    private String depName;

    @Column(name = "des", length = 50)
    private String depDescribe;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"employees"})
    private List<Employee> employees;

    public Department(int depNumber, String depName, String depDescribe) {
        this.depNumber = depNumber;
        this.depName = depName;
        this.depDescribe = depDescribe;
        this.employees = new ArrayList<>();
    }
}
