package uasz.sn.stage.Utilisateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.stage.Utilisateur.model.Administrateur;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {
}
