package com.hrms.soen6841.soen6841hrmsspringbootmysql.repository;

import com.hrms.soen6841.soen6841hrmsspringbootmysql.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByUsernameOrEmail(String username, String email);

    List<Employee> findByIdIn(List<Long> userIds);

    Optional<Employee> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
