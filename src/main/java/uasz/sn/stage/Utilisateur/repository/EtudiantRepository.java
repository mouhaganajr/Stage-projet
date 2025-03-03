package uasz.sn.stage.Utilisateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.stage.Utilisateur.model.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
