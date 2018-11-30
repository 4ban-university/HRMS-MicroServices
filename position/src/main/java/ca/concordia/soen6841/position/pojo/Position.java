package ca.concordia.soen6841.position.pojo;

import java.util.Date;

public class Position {

    private Integer id;
    private String designation;
    private String departmentName;
    private Date startDate;
    private Date endDate;
    private Long employeeId;

    public Position() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getEmployee() {
        return employeeId;
    }

    public void setEmployee(Long employeeId) {
        this.employeeId = employeeId;
    }
}
