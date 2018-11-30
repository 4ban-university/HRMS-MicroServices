package ca.concordia.soen6841.dbservice.controller;

import ca.concordia.soen6841.dbservice.exceptions.EmployeeNotFoundException;
import ca.concordia.soen6841.dbservice.model.Tax;
import ca.concordia.soen6841.dbservice.payloads.CustomResponse;
import ca.concordia.soen6841.dbservice.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tax")
public class TaxController {
    @Autowired
    private TaxRepository taxRepository;

    @GetMapping("/")
    public List<Tax> getTax() {
        return taxRepository.findAll();
    }

    @PostMapping("/")
    public CustomResponse<Tax> addTax(@RequestBody Tax newTax) {
        Tax tax = new Tax();
        tax.setTaxBracketMin(newTax.getTaxBracketMin());
        tax.setTaxBracketMax(newTax.getTaxBracketMax());
        tax.setFederalTax(newTax.getFederalTax());
        tax.setProvinceTax(newTax.getProvinceTax());
        tax.setProvince(newTax.getProvince());
        CustomResponse<Tax> response =  new CustomResponse<>();
        response.setData(taxRepository.save(tax));
        response.setMessage("Tax information saved successfully");
        return response;
    }

    @PutMapping("/{id}")
    public CustomResponse<Tax> editTax(@RequestBody Tax newTax, @PathVariable(value = "id", required = true) Long id) {
        taxRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        Tax tax = new Tax();
        tax.setTaxBracketMin(newTax.getTaxBracketMin());
        tax.setTaxBracketMax(newTax.getTaxBracketMax());
        tax.setFederalTax(newTax.getFederalTax());
        tax.setProvinceTax(newTax.getProvinceTax());
        tax.setProvince(newTax.getProvince());
        CustomResponse<Tax> response =  new CustomResponse<>();
        response.setData(taxRepository.save(tax));
        response.setMessage("Tax information edited successfully");
        return response;
    }

    @DeleteMapping("/{id}")
    public CustomResponse<Tax> deleteTax(@PathVariable(value = "id", required = true) Long id) {
        taxRepository.deleteById(id);
        CustomResponse<Tax> response =  new CustomResponse<>();
        response.setMessage("Tax deleted successfully");
        return response;
    }
}
