package ca.concordia.soen6841.employee.controller;

import ca.concordia.soen6841.employee.pojo.CustomResponse;
import ca.concordia.soen6841.employee.pojo.Employee;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
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
        return response.getBody();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.exchange(
                "http://localhost:8700/employee/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Employee>(){});
        return response.getBody();
    }

    @PutMapping("/{id}")
    public CustomResponse<Employee> editEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CustomResponse<Employee>> response = restTemplate.exchange(
                "http://localhost:8700/employee/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(newEmployee),
                new ParameterizedTypeReference<CustomResponse<Employee>>(){});
        return response.getBody();
    }

    @DeleteMapping("/{id}")
    public CustomResponse<Employee> deleteEmployee(@PathVariable Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CustomResponse<Employee>> response = restTemplate.exchange(
                "http://localhost:8700/employee/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<CustomResponse<Employee>>(){});
        return response.getBody();
    }
}
