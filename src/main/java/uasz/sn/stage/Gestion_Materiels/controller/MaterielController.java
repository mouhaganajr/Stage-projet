package uasz.sn.stage.Gestion_Materiels.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uasz.sn.stage.Authentification.modele.Utilisateur;
import uasz.sn.stage.Authentification.service.UtilisateurService;
import uasz.sn.stage.Gestion_Materiels.modele.Materiel;
import uasz.sn.stage.Gestion_Materiels.service.MaterielService;
import uasz.sn.stage.Gestion_Ufr.modele.Ufr;
import uasz.sn.stage.Gestion_Ufr.service.UfrService;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/materiels")
public class MaterielController {
    @Autowired
    private MaterielService materielService;

    @Autowired
    private UfrService ufrService;

    @Autowired
    private UtilisateurService utilisateurService;


    @GetMapping
    public String listerMateriels(@RequestParam(required = false) Long ufrId, Model model,Principal principal) {
        List<Materiel> materiels;

        if (ufrId != null) {
            materiels = materielService.listerMaterielParUFR(ufrId);
        } else {
            materiels = materielService.listerTous();
        }

        Utilisateur utilisateur = utilisateurService.getUtilisateurParUsername(principal.getName());
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));
        model.addAttribute("materiels", materiels);

        List<Ufr> ufrs = ufrService.literUfr();
        model.addAttribute("ufrs", ufrs);

        return "materiels";
    }




    @PostMapping("/ajouter")
    public String ajouterMateriel(@ModelAttribute Materiel materiel) {
        materielService.ajouterMateriel(materiel);
        return "redirect:/materiels";
    }

    @GetMapping("Resposable")
    public String listerMaterielsRESP(@RequestParam(required = false) Long ufrId, Model model,Principal principal) {
        List<Materiel> materiels;
        Utilisateur utilisateur = utilisateurService.getUtilisateurParUsername(principal.getName());
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));

        if (ufrId != null) {
            materiels = materielService.listerMaterielParUFR(ufrId);
        } else {
            materiels = materielService.listerTous();
        }


        model.addAttribute("materiels", materiels);

        List<Ufr> ufrs = ufrService.literUfr();
        model.addAttribute("ufrs", ufrs);

        return "materielsResp";
    }


}