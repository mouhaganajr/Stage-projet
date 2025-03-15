package uasz.sn.stage.Notification.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uasz.sn.stage.Authentification.modele.Utilisateur;
import uasz.sn.stage.Reservation.Modele.ReservationModele;
import uasz.sn.stage.Reservation.Repository.ReservationRepository;
import uasz.sn.stage.Utilisateur.model.Administrateur;
import uasz.sn.stage.Utilisateur.model.ResponsableUFR;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private uasz.sn.stage.Notification.Repository.NotificationRepository notificationRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    public void envoyerNotification(uasz.sn.stage.Notification.Modele.Notification notification) {
        notificationRepository.save(notification);
    }
    public List<uasz.sn.stage.Notification.Modele.Notification> findByDestinataire(Utilisateur destinataire) {
        return notificationRepository.findByDestinataire(destinataire);
    }

public Long nombreNotificationNonLu(Utilisateur destinataire) {
        return notificationRepository.nombreNotificationNonLu(destinataire);
    }
    public void lireNotification(Long id){
        uasz.sn.stage.Notification.Modele.Notification notification = notificationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Notification introuvable")
        );

        notification.setLu(true);
        notificationRepository.save(notification);

    }



    public void checkExpiredReservations() {
        LocalDateTime now = LocalDateTime.now();

        // Récupère toutes les réservations expirées qui n'ont pas été rendues
        List<ReservationModele> expiredReservations = reservationRepository.findByDateFinBeforeAndRenduFalse(now);

        for (ReservationModele reservation : expiredReservations) {
            // Envoie une notification à l'utilisateur
            envoyerNotification(reservation);
        }
    }

    private void envoyerNotification(ReservationModele reservation) {
        String message = "Bonjour " + reservation.getUtilisateur().getNom() + ", "
                + "La date de fin de votre réservation du matériel "
                + reservation.getMateriel().getNom()
                + " est atteinte. Veuillez le rendre au plus vite.";

        reservation.setMessage(message);

        // Sauvegarde de la réservation avec le message
        reservationRepository.save(reservation);

        // Log pour vérifier que la notification est bien envoyée (à remplacer par un vrai système de notification si besoin)
        System.out.println("Notification envoyée à l'utilisateur : " + reservation.getUtilisateur().getNom());
    }

    @Scheduled(cron = "0 0 0 * * ?") // Exécution tous les jours à minuit
    public void verifierReservationsExpirees() {
        checkExpiredReservations();
    }
}



