package uasz.sn.stage.Gestion_Ufr.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.stage.Gestion_Ufr.modele.Ufr;

@Repository
public interface UfrRepository extends JpaRepository<Ufr, Long> {
    boolean existsByNom(String nom);
}
