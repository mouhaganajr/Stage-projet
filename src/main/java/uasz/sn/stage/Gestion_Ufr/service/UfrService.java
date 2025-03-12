package uasz.sn.stage.Gestion_Ufr.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.stage.Gestion_Ufr.modele.Ufr;
import uasz.sn.stage.Gestion_Ufr.repository.UfrRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UfrService {

    @Autowired
    private UfrRepository ufrRepository;

    public List<Ufr> literUfr() {
        return ufrRepository.findAll();
    }

    public Optional<Ufr> getUfrById(Long id) {
        return ufrRepository.findById(id);
    }

    public Ufr createUfr(Ufr ufr) {
        if (ufrRepository.existsByNom(ufr.getNom())) {
            throw new RuntimeException("Une UFR avec ce nom existe déjà !");
        }
        return ufrRepository.save(ufr);
    }

    public Ufr updateUfr(Long id, Ufr ufrDetails) {
        return ufrRepository.findById(id).map(ufr -> {
            ufr.setNom(ufrDetails.getNom());
            ufr.setDescription(ufrDetails.getDescription());
            return ufrRepository.save(ufr);
        }).orElseThrow(() -> new RuntimeException("UFR non trouvée"));
    }

    public void deleteUfr(Long id) {
        if (!ufrRepository.existsById(id)) {
            throw new RuntimeException("UFR non trouvée");
        }
        ufrRepository.deleteById(id);
    }
}
