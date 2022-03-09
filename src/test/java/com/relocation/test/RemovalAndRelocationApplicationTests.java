package com.relocation.test;

import com.relocation.test.entity.Department;
import com.relocation.test.entity.Employee;
import com.relocation.test.repository.DepartmentRepository;
import com.relocation.test.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class RemovalAndRelocationApplicationTests {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    @Test
    @Transactional
    @Modifying
    @Rollback(false)
    public void addTest() {
//        Department department = new Department(1, "萌新部", "");
//        Employee e = new Employee(1, "ming", "pwd", "ji", null, false, "ji", "ji");
//        departmentRepository.save(department);
//        employeeRepository.save(e);

        Department d = departmentRepository.findDepartmentBydepNumber(1);
        Employee ee = employeeRepository.findEmployeeByeNum(1);
        ee.setDepartment(null);
        employeeRepository.save(ee);

        System.out.println(ee);
        System.out.println(d);
    }
}
