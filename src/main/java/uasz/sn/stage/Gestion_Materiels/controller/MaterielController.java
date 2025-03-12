package uasz.sn.stage.Gestion_Materiels.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uasz.sn.stage.Gestion_Materiels.modele.Materiel;
import uasz.sn.stage.Gestion_Materiels.service.MaterielService;
import uasz.sn.stage.Gestion_Ufr.modele.Ufr;
import uasz.sn.stage.Gestion_Ufr.service.UfrService;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/materiels")
public class MaterielController {
    @Autowired
    private MaterielService materielService;

    @Autowired
    private UfrService ufrService;


    @GetMapping
    public String listerMateriels(@RequestParam(required = false) Long ufrId, Model model) {
        List<Materiel> materiels;

        if (ufrId != null) {
            materiels = materielService.listerMaterielParUFR(ufrId);
        } else {
            materiels = materielService.listerTous();
        }


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

    @GetMapping("/modifier/{id}")
    public String afficherFormulaireModification(@PathVariable Long id, Model model) {
        Materiel materiel = materielService.listerMaterielParUFR(id).get(0); // Simplification pour l'exemple
        model.addAttribute("materiel", materiel);
        return "materiels/modifier";
    }

    @PostMapping("/modifier/{id}")
    public String mettreAJourMateriel(@PathVariable Long id, @ModelAttribute Materiel materiel) {
        materielService.modifierMateriel(id, materiel);
        return "redirect:/materiels?ufrId=" + materiel.getUfr().getId();
    }

    @GetMapping("/supprimer/{id}")
    public String supprimerMateriel(@PathVariable Long id) {
        Materiel materiel = materielService.listerMaterielParUFR(id).get(0); // Simplification pour l'exemple
        materielService.supprimerMateriel(id);
        return "redirect:/materiels?ufrId=" + materiel.getUfr().getId();
    }
}