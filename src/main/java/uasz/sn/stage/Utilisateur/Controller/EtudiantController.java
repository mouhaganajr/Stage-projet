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
import uasz.sn.stage.Utilisateur.model.Etudiant;
import uasz.sn.stage.Utilisateur.service.EtudiantService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private UtilisateurService utilisateurService;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    String password = passwordEncoder().encode("Passer123");


    @RequestMapping(value = "/responsable/ajouterEtudiant", method = RequestMethod.POST)
    public String ajouterEtudiant(Etudiant etudiant) {
        etudiant.setPassword(password);
        etudiant.setDateCreation(new Date());
        etudiant.setActive(true);

        // Récupérer le rôle existant ou le créer s'il n'existe pas
        Role role = utilisateurService.getOrCreateRole("ETUDIANT");

        List<Role> roles = new ArrayList<>();
        roles.add(role);
        etudiant.setRoles(roles);

        etudiantService.ajouterEtudiant(etudiant);
        return "redirect:/responsable/etudiant";
    }

    @RequestMapping(value = "/responsable/modifierEtudiant", method = RequestMethod.POST)
    public String modifierEtudiant( Etudiant etudiant) {
        // 1. Récupérer l'étudiant existant
        Etudiant existingEtudiant = etudiantService.getEtudiantById(etudiant.getId());

        // 2. Mettre à jour les champs modifiables
        existingEtudiant.setNom(etudiant.getNom());
        existingEtudiant.setPrenom(etudiant.getPrenom());
        existingEtudiant.setUsername(etudiant.getUsername());
        existingEtudiant.setActive(etudiant.isActive()); // Si nécessaire

        // 3. Gérer le rôle (éviter la duplication)
        Role role = utilisateurService.getOrCreateRole("ETUDIANT");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        existingEtudiant.setRoles(roles);

        // 4. Sauvegarder les modifications
        etudiantService.modifierEtudiant(existingEtudiant);

        return "redirect:/responsable/etudiant";
    }


    @RequestMapping(value = "/etudiant/dashboard",method = RequestMethod.GET)
    public String accueil_Etudiant(Model model, Principal principal){
        Utilisateur utilisateur=utilisateurService.getUtilisateurParUsername(principal.getName());
        model.addAttribute("nom",utilisateur.getNom());
        model.addAttribute("prenom",utilisateur.getPrenom().charAt(0));
        return "etudiant-dashboard";
    }

    @RequestMapping(value = "/responsable/etudiant", method = RequestMethod.GET)
    public String responsable_etudiant(Model model, Principal principal){
        List<Etudiant> etudiants=etudiantService.getTousLesEtudiants();
        model.addAttribute("etudiants",etudiants);
        Utilisateur utilisateur=utilisateurService.getUtilisateurParUsername(principal.getName());
        model.addAttribute("nom",utilisateur.getNom());
        model.addAttribute("prenom",utilisateur.getPrenom().charAt(0));
        return "responsableUFR-etudiant";
    }

}