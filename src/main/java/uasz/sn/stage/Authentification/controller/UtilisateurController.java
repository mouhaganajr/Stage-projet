package uasz.sn.stage.Authentification.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import uasz.sn.stage.Authentification.modele.Utilisateur;
import uasz.sn.stage.Authentification.service.UtilisateurService;

import java.security.Principal;

@Controller
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    /**
     * Page de connexion
     */
    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Redirige l'utilisateur après connexion en fonction de son rôle
     */
    @RequestMapping("/")
    public String loginRedirect(Principal principal) {
        if (principal == null) {
            return "redirect:/auth/login?error=not_authenticated";
        }

        Utilisateur utilisateur = utilisateurService.getUtilisateurParUsername(principal.getName());
        if (utilisateur == null || utilisateur.getRoles().isEmpty()) {
            return "redirect:/auth/login?error=no_role";
        }

        String role = utilisateur.getRoles().get(0).getRole();
        String url = "redirect:/auth/login?error=unknown_role"; // Par défaut

        switch (role) {
            case "ADMIN":
                url = "redirect:/admin/dashboard";
                break;
            case "ETUDIANT":
                url = "redirect:/etudiant/dashboard";
                break;
            case "RESPONSABLE_UFR":
                url = "redirect:/responsable/dashboard";
                break;
        }
        return url;
    }

    /**
     * Déconnexion de l'utilisateur
     */
    @RequestMapping("/logout")
    public String logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/auth/login?logout=true";
    }

    /**
     * Profil utilisateur
     */
    @RequestMapping("/profil")
    public String profilUtilisateur(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/auth/login?error=not_authenticated";
        }

        Utilisateur utilisateur = utilisateurService.getUtilisateurParUsername(principal.getName());
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));
        return "profil";
    }
}
