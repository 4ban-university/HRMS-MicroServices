package ca.concordia.soen6841.dbservice.model;

import javax.persistence.*;

@Entity
@Table(name = "Tax")
public class Tax extends AuditModel {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "tax_bracket_min")
    private Integer taxBracketMin;

    @Column(name = "tax_bracket_max")
    private Integer taxBracketMax;

    @Column(name = "province")
    private String province;

    @Column(name = "province_tax")
    private Integer provinceTax;

    @Column(name = "federal_tax")
    private Integer federalTax;

    public Tax() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProvinceTax(Integer provinceTax) {
        this.provinceTax = provinceTax;
    }

    public Integer getProvinceTax() {
        return provinceTax;
    }

    public Integer getFederalTax() {
        return federalTax;
    }

    public void setFederalTax(Integer federalTax) {
        this.federalTax = federalTax;
    }

    public Integer getTaxBracketMin() {
        return taxBracketMin;
    }

    public void setTaxBracketMin(Integer taxBracketMin) {
        this.taxBracketMin = taxBracketMin;
    }

    public Integer getTaxBracketMax() {
        return taxBracketMax;
    }

    public void setTaxBracketMax(Integer taxBracketMax) {
        this.taxBracketMax = taxBracketMax;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}
