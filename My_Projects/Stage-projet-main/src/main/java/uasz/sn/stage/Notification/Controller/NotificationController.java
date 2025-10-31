package uasz.sn.stage.Notification.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uasz.sn.Gestion_Enseignement.Authentification.modele.Utilisateur;
import uasz.sn.Gestion_Enseignement.Authentification.repository.UtilisateurRepository;
import uasz.sn.Gestion_Enseignement.Authentification.service.UtilisateurService;
import uasz.sn.Gestion_Enseignement.Notification.Modele.Notification;
import uasz.sn.Gestion_Enseignement.Notification.Service.NotificationService;
import uasz.sn.Gestion_Enseignement.Utilisateur.modele.Enseignant;
import uasz.sn.Gestion_Enseignement.Utilisateur.repository.EnseignantRepository;
import uasz.sn.Gestion_Enseignement.Utilisateur.service.EnseignantService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private EnseignantService enseignantService;
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/chefDepartement")
    public String chefDepartement(Model model, Principal principal) {
        Utilisateur utilisateur= utilisateurRepository.findUtilisateurByUsername(principal.getName());
        model.addAttribute("utilisateur", utilisateur);
        Enseignant enseignant= enseignantService.rechercher(utilisateur.getId());
        model.addAttribute("enseignant", enseignant);
        List<Notification> notifications= notificationService.findByDestinataire(enseignant);
        model.addAttribute("notifications", notifications);
        return "notification-chefDepartement";
    }
    @PostMapping("/lire/{id}")
    public String lireNotificationChefDepartement(@PathVariable("id") Long id) {
        notificationService.lireNotification(id);
        return "redirect:/notification/chefDepartement";
    }

    @GetMapping("/permanent")
    public String permanent(Model model, Principal principal){
        Utilisateur utilisateur= utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("utilisateur", utilisateur);
        Enseignant enseignant= enseignantService.rechercher(utilisateur.getId());
        model.addAttribute("enseignant", enseignant);
        List<Notification> notifications= notificationService.findByDestinataire(enseignant);
        model.addAttribute("notifications", notifications);
        return "notification-permanent";
    }
    @GetMapping("/vacataire")
    public String vacataire(Model model, Principal principal){
        Utilisateur utilisateur= utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("utilisateur", utilisateur);
        Enseignant enseignant= enseignantService.rechercher(utilisateur.getId());
        model.addAttribute("enseignant", enseignant);
        List<Notification> notifications= notificationService.findByDestinataire(enseignant);
        model.addAttribute("notifications", notifications);
        return "notification-vacataire";
    }

    @PostMapping("/lire/permanent/{id}")
    public String lireNotificationPermanent(@PathVariable("id") Long id){
        notificationService.lireNotification(id);
        return "redirect:/notification/permanent";
    }
    @PostMapping("/lire/vacataire/{id}")
    public String lireNotificationVacataire(@PathVariable("id") Long id){
        notificationService.lireNotification(id);
        return "redirect:/notification/vacataire";
    }

}
