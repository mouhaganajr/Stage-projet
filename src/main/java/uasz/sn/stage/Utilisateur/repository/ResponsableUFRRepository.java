package uasz.sn.stage.Utilisateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uasz.sn.stage.Utilisateur.model.ResponsableUFR;

import java.util.List;

public interface ResponsableUFRRepository extends JpaRepository<ResponsableUFR, Long> {
    @Query("SELECT r FROM ResponsableUFR r JOIN r.roles role WHERE role.role = :roleName")
    List<ResponsableUFR> findByRole(@Param("roleName") String roleName);


}
