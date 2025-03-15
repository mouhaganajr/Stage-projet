package uasz.sn.stage.Reservation.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uasz.sn.stage.Authentification.modele.Utilisateur;
import uasz.sn.stage.Authentification.service.UtilisateurService;
import uasz.sn.stage.Gestion_Materiels.modele.Materiel;
import uasz.sn.stage.Gestion_Materiels.service.MaterielService;
import uasz.sn.stage.Gestion_Ufr.modele.Ufr;
import uasz.sn.stage.Gestion_Ufr.service.UfrService;
import uasz.sn.stage.Notification.Service.NotificationService;
import uasz.sn.stage.Reservation.Modele.ReservationModele;
import uasz.sn.stage.Reservation.Service.ReservationService;
import uasz.sn.stage.Utilisateur.model.Etudiant;
import uasz.sn.stage.Utilisateur.service.EtudiantService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private MaterielService materielService;

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private UfrService ufrService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private EtudiantService etudiantService;

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @RequestMapping(value = "/etudiant/Reservation", method = RequestMethod.GET)
    public String listeReservation(Model model,
                                   @RequestParam(value = "utilisateurId", required = false) Long utilisateurId,
                                   @RequestParam(value = "ufrid", required = false) Long ufrid,Principal principal) {
        // Récupérer l'utilisateur connecté si `utilisateurId` n'est pas fourni
        if (utilisateurId == null) {
            Utilisateur utilisateur = utilisateurService.getUtilisateurConnecte();
            if (utilisateur == null) {
                throw new IllegalStateException("Utilisateur non trouvé. Veuillez vous connecter.");
            }
            utilisateurId = utilisateur.getId();
            model.addAttribute("utilisateur", utilisateur);
        }

        // Vérifier si l'utilisateur existe
        List<Utilisateur> utilisateurOpt = utilisateurService.getTousLesUtilisateurs();
        if (utilisateurOpt.isEmpty()) {
            throw new IllegalArgumentException("Utilisateur non trouvé.");
        }

        // Récupérer les réservations de l'utilisateur
        List<ReservationModele> reservations = reservationService.getReservationsParUtilisateur(utilisateurId);
        logger.info("Nombre de réservations trouvées pour l'utilisateur {}: {}", utilisateurId, reservations.size());
        model.addAttribute("reservations", reservations);

        // Charger les matériels en fonction de l'UFR sélectionnée
        List<Materiel> materiels = (ufrid != null) ? materielService.listerMaterielParUFR(ufrid) : materielService.listerTous();
        model.addAttribute("materiels", materiels);

        // Ajouter la liste des UFRs
        List<Ufr> ufrs = ufrService.literUfr();
        model.addAttribute("ufrs", ufrs);
        Utilisateur utilisateur = utilisateurService.getUtilisateurParUsername(principal.getName());
        Etudiant etudiant = etudiantService.getEtudiantById(utilisateur.getId());
        Long notificationNonLus= notificationService.nombreNotificationNonLu(etudiant);
        model.addAttribute("notificationsNonLus", notificationNonLus);
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));

        return "reservation";
    }



    @RequestMapping(value = "/reservation/faireUneReservation", method = RequestMethod.POST)
    public String ajouterUneReservation(@RequestParam("utilisateurId") Long utilisateurId,
                                        @RequestParam("materielId") Long materielId,
                                        @RequestParam("dateDebut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateDebut,
                                        @RequestParam("dateFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFin,
                                        RedirectAttributes redirectAttributes) {
        logger.info("Création d'une réservation pour utilisateur {} et matériel {} du {} au {}",
                utilisateurId, materielId, dateDebut, dateFin);

        // Vérifier si l'utilisateur existe
        Optional<Utilisateur> utilisateurOpt = Optional.ofNullable(utilisateurService.getUtilisateurParId(utilisateurId));
        if (!utilisateurOpt.isPresent()) {
            logger.error("Utilisateur introuvable avec ID {}", utilisateurId);
            redirectAttributes.addFlashAttribute("error", "Utilisateur introuvable.");
            return "redirect:/etudiant/Reservation";
        }

        // Vérifier si le matériel existe
        Optional<Materiel> materielOpt = Optional.ofNullable(materielService.getMaterielParId(materielId));
        if (!materielOpt.isPresent()) {
            logger.error("Matériel introuvable avec ID {}", materielId);
            redirectAttributes.addFlashAttribute("error", "Matériel introuvable.");
            return "redirect:/etudiant/Reservation";
        }

        // Vérifier que `dateDebut` est avant `dateFin`
        if (dateDebut.isAfter(dateFin)) {
            logger.error("Date de début {} est après la date de fin {}", dateDebut, dateFin);
            redirectAttributes.addFlashAttribute("error", "La date de début doit être avant la date de fin.");
            return "redirect:/etudiant/Reservation";
        }

        // Créer la réservation
        reservationService.creerReservation(utilisateurOpt.get().getId(), materielOpt.get().getId(), dateDebut, dateFin);

        redirectAttributes.addFlashAttribute("success", "Réservation créée avec succès.");
        return "redirect:/etudiant/Reservation";
    }



    @RequestMapping(value = "/reservation/approuver", method = RequestMethod.POST)
    public String approuverReservation(@RequestParam("reservationId") Long reservationId) {

        reservationService.approuverReservation(reservationId);
        return "redirect:/admin/Reservation";
    }

    @RequestMapping(value = "/reservation/rejeter", method = RequestMethod.POST)
    public String rejeterReservation(@RequestParam("reservationId") Long reservationId) {
        reservationService.rejeterReservation(reservationId);
        return "redirect:/admin/Reservation";
    }

    @RequestMapping(value = "/admin/Reservation", method = RequestMethod.GET)
    public String afficherReservation(Model model, Principal principal) {
        List<ReservationModele> reservations = reservationService.lister();
        logger.info("Nombre de réservations trouvées : " + reservations.size());

        List<Utilisateur> utilisateurs = utilisateurService.getTousLesUtilisateurs();
        logger.info("Nombre d'utilisateurs trouvés : " + utilisateurs.size());
        List<Materiel>materiels=materielService.listerTous();
        model.addAttribute("materiels",materiels);
        model.addAttribute("reservations", reservations);
        model.addAttribute("utilisateurs", utilisateurs);
        Utilisateur utilisateur = utilisateurService.getUtilisateurParUsername(principal.getName());
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));
        return "reservationRes";


    }


}
