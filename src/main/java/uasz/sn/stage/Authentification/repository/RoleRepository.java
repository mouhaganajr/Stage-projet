package uasz.sn.stage.Authentification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uasz.sn.stage.Authentification.modele.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRole(String role);

    @Query("SELECT r FROM Role r WHERE r.role = :role")
    Role findRoleByRole(@Param("role") String role);
}
