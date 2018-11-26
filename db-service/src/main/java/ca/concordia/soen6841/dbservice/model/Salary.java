package ca.concordia.soen6841.dbservice.model;

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

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Salary() {

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
