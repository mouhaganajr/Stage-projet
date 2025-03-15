package uasz.sn.stage.Notification.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.stage.Authentification.modele.Utilisateur;
import uasz.sn.stage.Utilisateur.model.Administrateur;
import uasz.sn.stage.Utilisateur.model.ResponsableUFR;


import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private uasz.sn.stage.Notification.Repository.NotificationRepository notificationRepository;

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


}

