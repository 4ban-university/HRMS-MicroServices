package com.hrms.soen6841.employee.controller;

import com.hrms.soen6841.employee.pojo.CustomResponse;
import com.hrms.soen6841.employee.pojo.Tax;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/tax")
public class TaxController {
    @GetMapping("/")
    public List<Tax> getTax() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Tax>> response = restTemplate.exchange(
                "http://localhost:8700/tax/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Tax>>(){});
        return response.getBody();
    }

    @PostMapping("/")
    public CustomResponse<Tax> addTax(@RequestBody Tax newTax) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CustomResponse<Tax>> response = restTemplate.exchange(
                "http://localhost:8700/tax/",
                HttpMethod.POST,
                new HttpEntity<>(newTax),
                new ParameterizedTypeReference<CustomResponse<Tax>>(){});
        return response.getBody();
    }

    @PutMapping("/{id}")
    public CustomResponse<Tax> editTax(@RequestBody Tax newTax, @PathVariable Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CustomResponse<Tax>> response = restTemplate.exchange(
                "http://localhost:8700/tax/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(newTax),
                new ParameterizedTypeReference<CustomResponse<Tax>>(){});
        return response.getBody();
    }

    @DeleteMapping("/{id}")
    public CustomResponse<Tax> deleteTax(@PathVariable Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CustomResponse<Tax>> response = restTemplate.exchange(
                "http://localhost:8700/tax/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<CustomResponse<Tax>>(){});
        return response.getBody();
    }
}
