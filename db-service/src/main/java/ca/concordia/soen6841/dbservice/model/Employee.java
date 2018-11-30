package ca.concordia.soen6841.dbservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Employee")
public class Employee extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    @NotBlank
    @Size(min = 4, max = 40)
    private String firstName;

    @Column(name = "last_name")
    @NotBlank
    @Size(min = 4, max = 40)
    private String lastName;

    @Column(name = "date_of_birth")
    @NotBlank
    private String dateOfBirth;

    @Column(name = "hiring_date")
    @NotBlank
    private String hiringDate;

    @Column(name = "province")
    @NotBlank
    private String province;

    @Column(name = "postal_code")
    @NotBlank
    private String postalCode;

    @Column(name = "username")
    @JsonIgnore
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column (name = "salary")
    private Long salary;

    @Column (name = "bonus")
    private Long bonus;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Employee_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Employee_jobPostings",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "jobposting_id"))
    private Set<JobPostings> Jobpostings = new HashSet<>();

    public Employee() {

    }

    public Set<JobPostings> getJobpostings() {
        return Jobpostings;
    }

    public void setJobpostings(Set<JobPostings> jobpostings) {
        Jobpostings = jobpostings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(String hiringDate) {
        this.hiringDate = hiringDate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getSalary() {return salary; }

    public void setSalary(Long salary) { this.salary = salary; }

    public Long getBonus() { return bonus; }

    public void setBonus(Long bonus) { this.bonus = bonus; }
}
