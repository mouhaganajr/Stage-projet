package uasz.sn.stage.Reservation.Service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.stage.Authentification.modele.Utilisateur;
import uasz.sn.stage.Authentification.repository.UtilisateurRepository;
import uasz.sn.stage.Gestion_Materiels.modele.Materiel;
import uasz.sn.stage.Gestion_Materiels.repository.MaterielRepository;

import uasz.sn.stage.Reservation.Controller.ReservationController;
import uasz.sn.stage.Reservation.Modele.ReservationModele;
import uasz.sn.stage.Reservation.Modele.Statut;
import uasz.sn.stage.Reservation.Repository.ReservationRepository;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;



@Service
@Transactional
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MaterielRepository materielRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);
    public ReservationModele creerReservation(Long utilisateurId, Long materielId, LocalDateTime dateDebut, LocalDateTime dateFin) {
        try {
            Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
            Materiel materiel = materielRepository.findById(materielId)
                    .orElseThrow(() -> new RuntimeException("Matériel non trouvé"));

            ReservationModele reservation = new ReservationModele();
            reservation.setUtilisateur(utilisateur);
            reservation.setMateriel(materiel);
            reservation.setDateDebut(dateDebut);
            reservation.setDateFin(dateFin);
            reservation.setStatut(Statut.ENATTENTE); // Direct reference to the enum constant

            return reservationRepository.save(reservation);
        } catch (Exception e) {
            // Log the exception (assuming a logger is available)
            logger.error("Erreur lors de la création de la réservation", e);
            // Handle the exception as needed, e.g., rethrow it or return a default value
            throw e;
        }
    }


    public List<ReservationModele> getReservationsParUtilisateur(Long utilisateurId) {
        return reservationRepository.findByUtilisateurId(utilisateurId);
    }
    public ReservationModele faireUneReservation(ReservationModele reservation) {
        return reservationRepository.save(reservation);
    }
    public void approuverReservation(Long reservationId) {
        ReservationModele reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));
        reservation.setMessage("Vous avez approuvé cette réservation.");
        reservation.setStatut(Statut.valueOf("APPROUVE"));
        reservationRepository.save(reservation);
    }

    public void rejeterReservation(Long reservationId) {
        ReservationModele reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));
        reservation.setMessage("Vous avez rejeté cette réservation.");
        reservation.setStatut(Statut.valueOf("REJETE"));
        reservationRepository.save(reservation);
    }

    public void supprimerReservation(Long reservationId) {reservationRepository.deleteById(reservationId);}

    public List<ReservationModele> lister(){return reservationRepository.findAll();
}
}
