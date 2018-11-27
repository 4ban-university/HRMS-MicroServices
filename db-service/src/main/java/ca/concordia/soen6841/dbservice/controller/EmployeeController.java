package ca.concordia.soen6841.dbservice.controller;

import ca.concordia.soen6841.dbservice.exceptions.EmployeeNotFoundException;
import ca.concordia.soen6841.dbservice.model.Employee;
import ca.concordia.soen6841.dbservice.model.Salary;
import ca.concordia.soen6841.dbservice.repository.EmployeeRepository;
import ca.concordia.soen6841.dbservice.repository.SalaryRepository;
import ca.concordia.soen6841.dbservice.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private TaxRepository taxRepository;

    @GetMapping("/")
    public List<Employee> getEmployee() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/{id}")
    public Employee editEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                    employee.setDateOfBirth(newEmployee.getDateOfBirth());
                    employee.setHiringDate(newEmployee.getHiringDate());
                    employee.setPostalCode(newEmployee.getPostalCode());
                    employee.setProvince(newEmployee.getProvince());
                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "Employee deleted successfully";
    }

    @PostMapping("/salary/{employeeId}")
    public String addSalary(@RequestBody Salary newSalary, @PathVariable Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
        Salary salary = new Salary();
        salary.setSalaryAmount(newSalary.getSalaryAmount());
        salary.setBonus(newSalary.getBonus());
        salary.setEmployee(employee);
        salaryRepository.save(salary);
        taxRepository.findByProvinceAndSalary(employee.getProvince(), salary.getSalaryAmount());
        /**
         * Calculation logic here
         */
        return "";
    }
}
