package ca.concordia.soen6841.employee.controller;

import ca.concordia.soen6841.employee.pojo.CustomResponse;
import ca.concordia.soen6841.employee.pojo.Salary;
import ca.concordia.soen6841.employee.pojo.Employee;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    @PostMapping("/{employeeId}")
    public CustomResponse<Employee> addSalary(@RequestBody Salary newSalary, @PathVariable Long employeeId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CustomResponse<Employee>> response = restTemplate.exchange(
                "http://localhost:8700/employee/salary/" + employeeId,
                HttpMethod.POST,
                new HttpEntity<>(newSalary),
                new ParameterizedTypeReference<CustomResponse<Employee>>(){});
        return response.getBody();
    }

}
