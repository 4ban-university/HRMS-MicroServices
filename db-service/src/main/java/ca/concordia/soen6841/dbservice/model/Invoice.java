package ca.concordia.soen6841.dbservice.model;

import javax.persistence.*;

@Entity
@Table(name = "Invoice")
public class Invoice extends AuditModel {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "salary_after_tax")
    private Long salaryAfterTax;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tax_id", nullable = false)
    private Tax tax;


    public Invoice() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSalaryAfterTax() {
        return salaryAfterTax;
    }

    public void setSalaryAfterTax(Long salaryAfterTax) {
        this.salaryAfterTax = salaryAfterTax;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }
}
