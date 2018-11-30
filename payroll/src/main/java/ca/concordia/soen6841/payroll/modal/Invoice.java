package ca.concordia.soen6841.payroll.modal;

public class Invoice {
    private String createdAt;
    private String updatedAt;
    private Integer id;
    private Integer salaryAfterTax;

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

    public Integer getSalaryAfterTax() {
        return salaryAfterTax;
    }

    public void setSalaryAfterTax(Integer salaryAfterTax) {
        this.salaryAfterTax = salaryAfterTax;
    }
}
