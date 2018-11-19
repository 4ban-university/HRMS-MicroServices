package com.hrms.soen6841.soen6841hrmsspringbootmysql.model;

import javax.persistence.*;

@Entity
@Table(name = "Salary")
public class Salary extends AuditModel {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "salary_amount")
    private Number salaryAmount;

    @Column(name = "bonus")
    private Number bonus;

    public Salary() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Number getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(Number salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public Number getBonus() {
        return bonus;
    }

    public void setBonus(Number bonus) {
        this.bonus = bonus;
    }
}
