package uasz.sn.stage.Gestion_Ufr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uasz.sn.stage.Gestion_Ufr.modele.Ufr;
import uasz.sn.stage.Gestion_Ufr.service.UfrService;

@Controller
@RequestMapping("/ufr")
public class UfrControler {

    @Autowired
    private UfrService ufrService;

    // Afficher la liste des UFRs
    @GetMapping
    public String getAllUfrs(Model model) {
        model.addAttribute("ufrs", ufrService.literUfr());
        return "ufr";
    }

    // Créer une UFR
    @PostMapping("/create")
    public String createUfr(@ModelAttribute Ufr ufr) {
        ufrService.createUfr(ufr);
        return "redirect:/ufr";
    }

    // Modifier une UFR
    @PostMapping("/update/{id}")
    public String updateUfr(@PathVariable Long id, @ModelAttribute Ufr ufr) {
        ufrService.updateUfr(id, ufr);
        return "redirect:/ufr";
    }

    // Supprimer une UFR
    @GetMapping("/delete/{id}")
    public String deleteUfr(@PathVariable Long id) {
        ufrService.deleteUfr(id);
        return "redirect:/ufr";
    }
}