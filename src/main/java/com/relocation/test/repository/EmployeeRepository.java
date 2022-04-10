package com.relocation.test.repository;

import com.relocation.test.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findEmployeeByNum(int num);
    boolean existsEmployeeByNameAndPwd(String name, String pwd);
    Employee findEmployeeByName(String name);
}
