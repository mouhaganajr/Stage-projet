package uasz.sn.stage.Utilisateur.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.stage.Authentification.modele.Role;
import uasz.sn.stage.Authentification.repository.RoleRepository;
import uasz.sn.stage.Utilisateur.model.Etudiant;
import uasz.sn.stage.Utilisateur.repository.EtudiantRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional

public class EtudiantService {

    @Autowired
    private  EtudiantRepository etudiantRepository;
    @Autowired
    private RoleRepository roleRepository;

    public Etudiant ajouterEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }


    public List<Etudiant> getTousLesEtudiants() {
        return etudiantRepository.findAll();
    }

    public void supprimerEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }

    public Etudiant modifierEtudiant(Etudiant etudiant) {
        // Utilise la méthode save() de JPA pour mettre à jour
        return etudiantRepository.save(etudiant);
    }

    public Etudiant getEtudiantById(Long id) {
        // Utilisation de findById() de JPA qui retourne un Optional
        return etudiantRepository.findById(id).get();
    }
}
