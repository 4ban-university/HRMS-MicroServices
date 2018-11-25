package com.hrms.soen6841.employee.repository;

import com.hrms.soen6841.employee.model.Role;
import com.hrms.soen6841.employee.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
