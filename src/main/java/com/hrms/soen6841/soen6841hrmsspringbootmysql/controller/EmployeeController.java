package com.hrms.soen6841.soen6841hrmsspringbootmysql.controller;

import com.hrms.soen6841.soen6841hrmsspringbootmysql.model.Employee;
import com.hrms.soen6841.soen6841hrmsspringbootmysql.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public Page<Employee> getEmployee(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
}
