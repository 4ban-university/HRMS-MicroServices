package com.hrms.soen6841.employee.model;

import javax.persistence.*;

@Entity
@Table(name = "Tax")
public class Tax extends AuditModel {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Lob
    @Column(name = "tax_brackets")
    private String taxBrackets;

    @Column(name = "province")
    private String province;

    @Column(name = "province_tax")
    private Number provinceTax;

    @Column(name = "federal_tax")
    private Number federalTax;

    public Tax() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaxBrackets() {
        return taxBrackets;
    }

    public void setTaxBrackets(String taxBrackets) {
        this.taxBrackets = taxBrackets;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Number getProvinceTax() {
        return provinceTax;
    }

    public void setProvinceTax(Number provinceTax) {
        this.provinceTax = provinceTax;
    }

    public Number getFederalTax() {
        return federalTax;
    }

    public void setFederalTax(Number federalTax) {
        this.federalTax = federalTax;
    }
}
