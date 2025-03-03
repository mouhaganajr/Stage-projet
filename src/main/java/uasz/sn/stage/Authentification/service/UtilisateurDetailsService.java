package uasz.sn.stage.Authentification.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.stage.Authentification.modele.Role;
import uasz.sn.stage.Authentification.modele.Utilisateur;
import uasz.sn.stage.Authentification.repository.UtilisateurRepository;

import java.util.Optional;

@Service
public class UtilisateurDetailsService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    @Transactional(readOnly = true) // ✅ Évite LazyInitializationException
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByUsername(username);

        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé : " + username);
        }

        String[] roles = utilisateur.getRoles().stream()
                .map(Role::getRole) // Assure-toi que `role.getRole()` retourne un String valide
                .toArray(String[]::new);

        return org.springframework.security.core.userdetails.User.builder()
                .username(utilisateur.getUsername())
                .password(utilisateur.getPassword())
                .roles(roles)
                .build();
    }
}
