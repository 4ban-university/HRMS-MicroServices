package ca.concordia.soen6841.dbservice.model;

import javax.persistence.*;

@Entity
@Table(name = "Tax")
public class Tax {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "tax_bracket_min")
    private Long taxBracketMin;

    @Column(name = "tax_bracket_max")
    private Long taxBracketMax;

    @Column(name = "province")
    private String province;

    @Column(name = "province_tax")
    private Long provinceTax;

    @Column(name = "federal_tax")
    private Long federalTax;

    public Tax() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaxBracketMin() {
        return taxBracketMin;
    }

    public void setTaxBracketMin(Long taxBracketMin) {
        this.taxBracketMin = taxBracketMin;
    }

    public Long getTaxBracketMax() {
        return taxBracketMax;
    }

    public void setTaxBracketMax(Long taxBracketMax) {
        this.taxBracketMax = taxBracketMax;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Long getProvinceTax() {
        return provinceTax;
    }

    public void setProvinceTax(Long provinceTax) {
        this.provinceTax = provinceTax;
    }

    public Long getFederalTax() {
        return federalTax;
    }

    public void setFederalTax(Long federalTax) {
        this.federalTax = federalTax;
    }
}
