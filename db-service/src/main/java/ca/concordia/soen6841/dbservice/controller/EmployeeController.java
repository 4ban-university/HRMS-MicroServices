package ca.concordia.soen6841.dbservice.controller;

import ca.concordia.soen6841.dbservice.exceptions.EmployeeNotFoundException;
import ca.concordia.soen6841.dbservice.model.Employee;
import ca.concordia.soen6841.dbservice.model.Invoice;
import ca.concordia.soen6841.dbservice.model.Tax;
import ca.concordia.soen6841.dbservice.payloads.CustomResponse;
import ca.concordia.soen6841.dbservice.pojo.Salary;
import ca.concordia.soen6841.dbservice.repository.EmployeeRepository;
import ca.concordia.soen6841.dbservice.repository.InvoiceRepository;
import ca.concordia.soen6841.dbservice.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping("/")
    public List<Employee> getEmployee() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id", required = true) Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/{id}")
    public CustomResponse<Employee> editEmployee(@Valid @RequestBody Employee newEmployee, @PathVariable(value = "id", required = true) Long id) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        Employee updatedEmp = new Employee();
        updatedEmp.setFirstName(newEmployee.getFirstName());
        updatedEmp.setLastName(newEmployee.getLastName());
        updatedEmp.setDateOfBirth(newEmployee.getDateOfBirth());
        updatedEmp.setHiringDate(newEmployee.getHiringDate());
        updatedEmp.setPostalCode(newEmployee.getPostalCode());
        updatedEmp.setProvince(newEmployee.getProvince());
        CustomResponse<Employee> response =  new CustomResponse<>();
        response.setData(employeeRepository.save(updatedEmp));
        response.setMessage("Employee information saved successfully");
        return response;
    }

    @DeleteMapping("/{id}")
    public CustomResponse<Employee> deleteEmployee(@PathVariable(value = "id", required = true) Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.deleteById(id);
        CustomResponse<Employee> response =  new CustomResponse<>();
        response.setData(employeeRepository.save(employee));
        response.setMessage("Employee deleted successfully");
        return response;
    }

    @PostMapping("/salary/{employeeId}")
    public CustomResponse<Employee> addSalary(@RequestBody Salary newSalary, @PathVariable(value = "employeeId", required = true) Long employeeId) {
        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
        Employee updatedEmp = new Employee();
        updatedEmp.setSalary(newSalary.getSalary());
        updatedEmp.setBonus(newSalary.getBonus());
        CustomResponse<Employee> response =  new CustomResponse<>();
        response.setData(employeeRepository.save(updatedEmp));
        response.setMessage("Salary added successfully");
        return response;
    }

    @GetMapping("/invoice/{id}")
    public CustomResponse<Invoice> generateInvoice(@PathVariable(value = "id", required = true) Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        Tax tax = taxRepository.findByProvinceAndSalary(employee.getProvince(), employee.getSalary());
        Long totalSalary = ( employee.getSalary() ) - (employee.getSalary() * tax.getFederalTax() / 100 ) +
                ( employee.getSalary() * tax.getProvinceTax() / 100 ) +
                ( employee.getBonus());
        Invoice invoice =  new Invoice();
        invoice.setSalaryAfterTax(totalSalary);
        invoice.setEmployee(employee);
        invoice.setTax(tax);
        CustomResponse<Invoice> response =  new CustomResponse<>();
        response.setData(invoiceRepository.save(invoice));
        response.setMessage("Invoice generated successfully");
        return response;
    }
}
