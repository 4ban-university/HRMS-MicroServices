package ca.concordia.soen6841.payroll.controller;

import ca.concordia.soen6841.payroll.modal.CustomResponse;
import ca.concordia.soen6841.payroll.modal.Invoice;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class PayrollController {
    @GetMapping("/invoice/{id}")
    public CustomResponse<Invoice> generateInvoice(@PathVariable Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CustomResponse<Invoice>> response = restTemplate.exchange(
                "http://localhost:8700/employee/invoice" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CustomResponse<Invoice>>(){});
        return response.getBody();
    }
}
