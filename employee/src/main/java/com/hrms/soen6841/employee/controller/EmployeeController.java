package com.hrms.soen6841.employee.controller;

import com.hrms.soen6841.employee.pojo.Employee;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/")
public class EmployeeController {

    @GetMapping("/")
    public List<Employee> getEmployeesList() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Employee>> response = restTemplate.exchange(
                "http://localhost:8700/employee/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>(){});
        List<Employee> employees = response.getBody();
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.exchange(
                "http://localhost:8700/employee/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Employee>(){});
        Employee employee = response.getBody();
        return employee;
    }
}
