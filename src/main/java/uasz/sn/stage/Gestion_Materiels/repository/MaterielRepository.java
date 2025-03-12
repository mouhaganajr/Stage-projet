package uasz.sn.stage.Gestion_Materiels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.stage.Gestion_Materiels.modele.Materiel;

import java.util.List;
@Repository
public interface MaterielRepository extends JpaRepository<Materiel, Long> {
    List<Materiel> findByUfrId(Long ufrId);
}
