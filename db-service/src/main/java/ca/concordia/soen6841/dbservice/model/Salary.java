package ca.concordia.soen6841.dbservice.model;

import javax.persistence.*;

@Entity
@Table(name = "Salary")
public class Salary extends AuditModel {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "salary_amount")
    private Integer salaryAmount;

    @Column(name = "bonus")
    private Integer bonus;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(Integer salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }
}
