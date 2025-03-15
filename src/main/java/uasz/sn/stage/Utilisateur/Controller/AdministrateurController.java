package uasz.sn.stage.Utilisateur.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uasz.sn.stage.Authentification.modele.Utilisateur;
import uasz.sn.stage.Authentification.service.UtilisateurService;
import uasz.sn.stage.Utilisateur.model.Administrateur;
import uasz.sn.stage.Utilisateur.service.AdministrateurService;

import java.security.Principal;
import java.util.List;

@Controller
public class AdministrateurController {

    @Autowired
    private AdministrateurService administrateurService;
    @Autowired
    private UtilisateurService utilisateurService;

    @RequestMapping("/ajouter")
    public String ajouterAdministrateur(@ModelAttribute Administrateur administrateur) {
        administrateurService.ajouterAdministrateur(administrateur);
        return "redirect:/administrateurs/tous";
    }


    @RequestMapping(value = "/admin/dashboard",method = RequestMethod.GET)
    public String accueil_Administrateur(Model model, Principal principal){
        Utilisateur utilisateur=utilisateurService.getUtilisateurParUsername(principal.getName());
        model.addAttribute("nom",utilisateur.getNom());
        model.addAttribute("prenom",utilisateur.getPrenom().charAt(0));

        return "admin-dashboard";
    }

    @RequestMapping("/tous")
    public List<Administrateur> getTousLesAdministrateurs() {
        return administrateurService.getTousLesAdministrateurs();
    }

    @RequestMapping("/supprimer/{id}")
    public String supprimerAdministrateur(@PathVariable Long id) {
        administrateurService.supprimerAdministrateur(id);
        return "redirect:/administrateurs/tous";
    }
}