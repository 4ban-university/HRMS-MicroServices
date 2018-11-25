package ca.concordia.soen6841.dbservice.repository;

import com.hrms.soen6841.dbservice.model.Role;
import com.hrms.soen6841.dbservice.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
