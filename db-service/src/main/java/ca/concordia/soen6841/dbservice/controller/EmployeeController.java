package ca.concordia.soen6841.dbservice.controller;

import ca.concordia.soen6841.dbservice.exceptions.EmployeeNotFoundException;
import ca.concordia.soen6841.dbservice.model.Employee;
import ca.concordia.soen6841.dbservice.model.Invoice;
import ca.concordia.soen6841.dbservice.model.Tax;
import ca.concordia.soen6841.dbservice.pojo.Response;
import ca.concordia.soen6841.dbservice.pojo.Salary;
import ca.concordia.soen6841.dbservice.repository.EmployeeRepository;
import ca.concordia.soen6841.dbservice.repository.InvoiceRepository;
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
    private TaxRepository taxRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping("/")
    public Response<List<Employee>> getEmployee() {
        Response<List<Employee>> response = new Response<>();
        response.setMessage("List of employees");
        response.setData(employeeRepository.findAll());
        return response;
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
        employeeRepository.findById(employeeId)
                .map(employee -> {
                    employee.setSalary(newSalary.getSalary());
                    employee.setBonus(newSalary.getBonus());
                    return employeeRepository.save(employee);
                });
        return "Salary added successfully";
    }

    @GetMapping("/invoice/{id}")
    public Invoice generateInvoice(@PathVariable Long id) {
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
        return invoiceRepository.save(invoice);
    }
}
