package com.hrms.soen6841.employee.pojo;

public class Tax {
    private String createdAt;
    private String updatedAt;
    private Integer id;
    private Integer taxBracketMin;
    private Integer taxBracketMax;
    private String province;
    private Integer provinceTax;
    private Integer federalTax;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getProvinceTax() {
        return provinceTax;
    }

    public void setProvinceTax(Integer provinceTax) {
        this.provinceTax = provinceTax;
    }

    public Integer getFederalTax() {
        return federalTax;
    }

    public void setFederalTax(Integer federalTax) {
        this.federalTax = federalTax;
    }
}
