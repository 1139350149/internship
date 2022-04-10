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
}
