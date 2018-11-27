package ca.concordia.soen6841.dbservice.controller;

import ca.concordia.soen6841.dbservice.model.Tax;
import ca.concordia.soen6841.dbservice.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/payroll")
public class PayrollController {
    @Autowired
    private TaxRepository taxRepository;

    @GetMapping("/tax")
    public List<Tax> getTax() {
        return taxRepository.findAll();
    }

    @PostMapping("/tax")
    public Tax addTax(@RequestBody Tax newTax) {
        Tax tax = new Tax();
        tax.setTaxBracketMin(newTax.getTaxBracketMin());
        tax.setTaxBracketMax(newTax.getTaxBracketMax());
        tax.setFederalTax(newTax.getFederalTax());
        tax.setProvinceTax(newTax.getProvinceTax());
        tax.setProvince(newTax.getProvince());
        return taxRepository.save(tax);
    }

    @PutMapping("/tax/{id}")
    public Tax editTax(@RequestBody Tax newTax, @PathVariable Long id) {
        return taxRepository.findById(id)
                .map(tax -> {
                    tax.setTaxBracketMin(newTax.getTaxBracketMin());
                    tax.setTaxBracketMax(newTax.getTaxBracketMax());
                    tax.setFederalTax(newTax.getFederalTax());
                    tax.setProvinceTax(newTax.getProvinceTax());
                    tax.setProvince(newTax.getProvince());
                    return taxRepository.save(tax);
                })
                .orElseGet(() -> {
                    newTax.setId(id);
                    return taxRepository.save(newTax);
                });
    }

    @DeleteMapping("/tax/{id}")
    public String deleteTax(@PathVariable Long id) {
        taxRepository.deleteById(id);
        return "Tax deleted successfully";
    }
}
