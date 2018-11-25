package com.hrms.soen6841.employee.model;

import javax.persistence.*;

@Entity
@Table(name = "Invoice")
public class Invoice extends AuditModel {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "salary_after_tax")
    private Number salaryAfterTax;

    public Invoice() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Number getSalaryAfterTax() {
        return salaryAfterTax;
    }

    public void setSalaryAfterTax(Number salaryAfterTax) {
        this.salaryAfterTax = salaryAfterTax;
    }
}
