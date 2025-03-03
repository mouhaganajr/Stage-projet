package uasz.sn.stage.Utilisateur.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.stage.Utilisateur.model.Administrateur;
import uasz.sn.stage.Utilisateur.repository.AdministrateurRepository;

import java.util.List;

@Service
@Transactional
public class AdministrateurService {
@Autowired
private  AdministrateurRepository administrateurRepository;

    public Administrateur ajouterAdministrateur(Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }

    public List<Administrateur> getTousLesAdministrateurs() {
        return administrateurRepository.findAll();
    }

    public void supprimerAdministrateur(Long id) {
        administrateurRepository.deleteById(id);
    }
}
