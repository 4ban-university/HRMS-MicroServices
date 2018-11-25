package ca.concordia.soen6841.auth.repository;

import ca.concordia.soen6841.auth.model.Role;
import ca.concordia.soen6841.auth.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
