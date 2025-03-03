package uasz.sn.stage.Utilisateur.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.stage.Utilisateur.model.ResponsableUFR;
import uasz.sn.stage.Utilisateur.repository.ResponsableUFRRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResponsableUFRService {
    @Autowired
    private  ResponsableUFRRepository responsableUFRRepository;


    public ResponsableUFR ajouterResponsableUFR(ResponsableUFR responsable) {
        return responsableUFRRepository.save(responsable);
    }

    public List<ResponsableUFR> getTousLesResponsables() {
        return responsableUFRRepository.findAll();
    }

    public Optional<ResponsableUFR> getResponsableParId(Long id) {
        return responsableUFRRepository.findById(id);
    }

    public void supprimerResponsableUFR(Long id) {
        responsableUFRRepository.deleteById(id);
    }

    public ResponsableUFR modifierResponsableUFR(Long id, ResponsableUFR responsableDetails) {
        return responsableUFRRepository.findById(id).map(responsable -> {
            responsable.setNom(responsableDetails.getNom());
            responsable.setPrenom(responsableDetails.getPrenom());
            responsable.setUsername(responsableDetails.getUsername());
            responsable.setUfr(responsableDetails.getUfr()); // Mise à jour de l'UFR
            return responsableUFRRepository.save(responsable);
        }).orElseThrow(() -> new RuntimeException("Responsable UFR non trouvé"));
    }

    public void archiver(Long id){
        ResponsableUFR responsableUFR=responsableUFRRepository.findById(id).get();
        if (responsableUFR.isArchiver()){
            responsableUFR.setArchiver(false);
        }
        else {
            responsableUFR.setArchiver(true);
        }
        responsableUFRRepository.save(responsableUFR);
    }
    public List<ResponsableUFR> getAllResponsables() {
        return responsableUFRRepository.findByRole("RESPONSABLE_UFR");
    }


}
