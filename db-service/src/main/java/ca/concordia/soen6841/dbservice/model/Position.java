package ca.concordia.soen6841.dbservice.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Position")
public class Position extends AuditModel {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "designation")
    private String designation;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

//    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employeeId", nullable = false)
    private Long employeeId;


    public Position() {

    }

    public Long getEmployee() {
        return employeeId;
    }

    public void setEmployee(Long employeeId) {
        this.employeeId = employeeId;
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
}
