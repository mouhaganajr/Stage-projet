package uasz.sn.stage.Notification.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uasz.sn.stage.Authentification.modele.Utilisateur;
import uasz.sn.stage.Authentification.service.UtilisateurService;
import uasz.sn.stage.Notification.Modele.Notification;
import uasz.sn.stage.Utilisateur.model.Etudiant;
import uasz.sn.stage.Utilisateur.model.ResponsableUFR;
import uasz.sn.stage.Utilisateur.service.EtudiantService;
import uasz.sn.stage.Utilisateur.service.ResponsableUFRService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("notification")
public class NotificationController {
    @Autowired
    private uasz.sn.stage.Notification.Service.NotificationService notificationService;
    @Autowired
    private uasz.sn.stage.Authentification.repository.UtilisateurRepository utilisateurRepository;
    @Autowired
    private ResponsableUFRService responsableUFRService;
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/responsable")
    public String responsable(Model model, Principal principal) {
        Utilisateur utilisateur= utilisateurRepository.findUtilisateurByUsername(principal.getName());
        model.addAttribute("utilisateur", utilisateur);
        ResponsableUFR responsable= responsableUFRService.getResponsableParId(utilisateur.getId())
                .orElseThrow(() -> new RuntimeException("Responsable UFR non trouvé pour l'ID : " + utilisateur.getId()));        model.addAttribute("responsable", responsable);
        ;
        model.addAttribute("responsable", responsable);
        List<Notification> notifications= notificationService.findByDestinataire(responsable);
        model.addAttribute("notifications", notifications);
        return "notification-responsable";
    }
    @PostMapping("/lire/{id}")
    public String lireNotificationResponsable(@PathVariable("id") Long id) {
        notificationService.lireNotification(id);
        return "redirect:/notification/responsable";
    }


    @GetMapping("/etudiant")
    public String etudiant(Model model, Principal principal){
        Utilisateur utilisateur= utilisateurService.getUtilisateurParUsername(principal.getName());
        model.addAttribute("utilisateur", utilisateur);
        Etudiant etudiant = etudiantService.getEtudiantById(utilisateur.getId());
        List<Notification> notifications= notificationService.findByDestinataire(etudiant);
        model.addAttribute("notifications", notifications);
        return "notification-etudiant";
    }


    @PostMapping("/lire/etudiant/{id}")
    public String lireNotificationEtudiant(@PathVariable("id") Long id){
        notificationService.lireNotification(id);
        return "redirect:/notification/etudiant";
    }

}
