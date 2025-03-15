package uasz.sn.stage.Notification.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uasz.sn.stage.Authentification.modele.Utilisateur;
import uasz.sn.stage.Notification.Modele.Notification;
import uasz.sn.stage.Utilisateur.model.Administrateur;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<uasz.sn.stage.Notification.Modele.Notification, Long> {
    List<uasz.sn.stage.Notification.Modele.Notification> findByDestinataire(Utilisateur destinataire);

    @Query("select count(n) from Notification n where n.destinataire= :destinataire and n.lu=false")
    Long nombreNotificationNonLu(@Param("destinataire") Utilisateur destinataire);
}
