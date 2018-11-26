package ca.concordia.soen6841.dbservice.controller;

import ca.concordia.soen6841.dbservice.exceptions.EmployeeNotFoundException;
import ca.concordia.soen6841.dbservice.model.Employee;
import ca.concordia.soen6841.dbservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public List<Employee> getEmployee() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }
}
