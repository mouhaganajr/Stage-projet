package uasz.sn.stage.Authentification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.stage.Authentification.modele.Role;
import uasz.sn.stage.Authentification.modele.Utilisateur;
import uasz.sn.stage.Authentification.repository.RoleRepository;
import uasz.sn.stage.Authentification.repository.UtilisateurRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UtilisateurService {

    @Autowired
    private  UtilisateurRepository utilisateurRepository;
    @Autowired

    private  RoleRepository roleRepository;
    @Autowired

    private  PasswordEncoder passwordEncoder;

    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur, Role admin) {
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword())); // Hash du mot de passe
        return utilisateurRepository.save(utilisateur);
    }

    public Role ajouter_Role(Role role) {
        roleRepository.save(role);
        return role;
    }

    public void ajouter_UtilisateurRoles(Utilisateur utilisateur, Role role) {
        Utilisateur user = utilisateurRepository.findUtilisateurByUsername(utilisateur.getUsername());
        Role profil = roleRepository.findRoleByRole(role.getRole());
        user.getRoles().add(profil);
    }


    public Utilisateur getUtilisateurParUsername(String username) {
        return utilisateurRepository.findUtilisateurByUsername(username);
    }


    public List<Utilisateur> getTousLesUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public void supprimerUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }


    public Role getOrCreateRole(String roleName) {
        // Essayer de récupérer le rôle existant
        Role role = roleRepository.findByRole(roleName);

        // Si le rôle n'existe pas, le créer
        if (role == null) {
            role = new Role(roleName);
            roleRepository.save(role);
        }

        return role;
    }

    public Utilisateur getUtilisateurConnecte() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            String email = userDetails.getUsername(); // ✅ Récupération correcte de l'email
            return utilisateurRepository.findByUsername(email)
                    .orElseThrow(() -> new IllegalStateException("Utilisateur non trouvé"));
        }
        throw new IllegalStateException("Utilisateur non authentifié");
    }
    public Utilisateur getUtilisateurParId(Long utilisateurId) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findById(utilisateurId);
        if (utilisateurOpt.isPresent()) {
            return utilisateurOpt.get();
        } else {
            throw new RuntimeException("Utilisateur non trouvé avec l'ID : " + utilisateurId);
        }
    }


}
