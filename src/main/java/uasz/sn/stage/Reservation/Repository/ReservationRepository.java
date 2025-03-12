package uasz.sn.stage.Reservation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.stage.Reservation.Modele.ReservationModele;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationModele,Long> {
    List<ReservationModele> findByUtilisateurId(Long utilisateurId);
}
