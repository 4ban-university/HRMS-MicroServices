package com.hrms.soen6841.employee.controller;

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
    public Tax addTax(@RequestBody Tax newTax) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Tax> response = restTemplate.exchange(
                "http://localhost:8700/tax/",
                HttpMethod.POST,
                new HttpEntity<>(newTax),
                Tax.class);
        return response.getBody();
    }

    @PutMapping("/{id}")
    public Tax editTax(@RequestBody Tax newTax, @PathVariable Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Tax> response = restTemplate.exchange(
                "http://localhost:8700/tax/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(newTax),
                Tax.class);
        return response.getBody();
    }

    @DeleteMapping("/{id}")
    public String deleteTax(@PathVariable Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8700/tax/" + id,
                HttpMethod.DELETE,
                null,
                String.class);
        return response.getBody();
    }
}
