package uasz.sn.stage.Utilisateur.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uasz.sn.stage.Authentification.modele.Role;
import uasz.sn.stage.Authentification.modele.Utilisateur;
import uasz.sn.stage.Authentification.service.UtilisateurService;
import uasz.sn.stage.Utilisateur.model.Administrateur;
import uasz.sn.stage.Utilisateur.model.Etudiant;
import uasz.sn.stage.Utilisateur.model.ResponsableUFR;
import uasz.sn.stage.Utilisateur.service.EtudiantService;
import uasz.sn.stage.Utilisateur.service.ResponsableUFRService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ResponsableUFRController {

    @Autowired
    private ResponsableUFRService responsableUFRService;
    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private EtudiantService etudiantService;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    String password = passwordEncoder().encode("Passer123");

    @RequestMapping(value = "/admin/ajouterResponsable", method = RequestMethod.POST)
    public String ajouterResponsable(ResponsableUFR responsable) {
        responsable.setPassword(password);
        responsable.setDateCreation(new Date());
        responsable.setActive(true);

        // Récupérer le rôle existant ou le créer s'il n'existe pas
        Role role = utilisateurService.getOrCreateRole("RESPONSABLE_UFR");

        List<Role> roles = new ArrayList<>();
        roles.add(role);
        responsable.setRoles(roles);

        responsableUFRService.ajouterResponsableUFR(responsable);
        return "redirect:/admin/listeResponsables";
    }


    @RequestMapping(value = "/admin/modifierResponsable", method = RequestMethod.POST)
    public String modifierResponsable(ResponsableUFR responsable) {
        ResponsableUFR responsableModif = responsableUFRService.getResponsableParId(responsable.getId()).orElse(null);

        if (responsableModif != null) {
            responsableModif.setNom(responsable.getNom());
            responsableModif.setPrenom(responsable.getPrenom());
            responsableModif.setUsername(responsable.getUsername());
            responsableModif.setUfr(responsable.getUfr());
            responsableUFRService.ajouterResponsableUFR(responsableModif);
        }

        return "redirect:/admin/listeResponsables";
    }

    @RequestMapping(value = "/admin/archiverResponsable/{id}", method = RequestMethod.GET)
    public String archiverResponsable(@PathVariable Long id) {
        responsableUFRService.archiver(id);
        return "redirect:/admin/listeResponsables";
    }

    @RequestMapping(value = "/admin/listeResponsables", method = RequestMethod.GET)
    public String admin_responsable(Model model, Principal principal){
        List<ResponsableUFR> responsableUFRS=responsableUFRService.getAllResponsables();
        model.addAttribute("responsableUFRS",responsableUFRS);

        Utilisateur utilisateur=utilisateurService.getUtilisateurParUsername(principal.getName());
        model.addAttribute("nom",utilisateur.getNom());
        model.addAttribute("prenom",utilisateur.getPrenom().charAt(0));
        return "admin-responsableufr";
    }




    @RequestMapping(value = "/responsable/dashboard",method = RequestMethod.GET)
    public String accueil_Responsable(Model model, Principal principal){
        Utilisateur utilisateur=utilisateurService.getUtilisateurParUsername(principal.getName());
        ResponsableUFR responsable= responsableUFRService.getResponsableParId(utilisateur.getId())
                .orElseThrow(() -> new RuntimeException("Responsable UFR non trouvé pour l'ID : " + utilisateur.getId()));        model.addAttribute("responsable", responsable);

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("nom",utilisateur.getNom());
        model.addAttribute("prenom",utilisateur.getPrenom().charAt(0));
        return "responsable-dashboard";
    }
}
