package uasz.sn.stage.Gestion_Materiels.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.stage.Gestion_Materiels.modele.Categorie;
import uasz.sn.stage.Gestion_Materiels.modele.Materiel;
import uasz.sn.stage.Gestion_Materiels.repository.MaterielRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MaterielService {
    @Autowired
    private MaterielRepository materielRepository;

    public List<Materiel> listerMaterielParUFR(Long ufrId) {
        return materielRepository.findByUfrId(ufrId);
    }

    public Materiel ajouterMateriel(Materiel materiel) {
        materiel.setImage(genererImage(materiel.getCategorie()));
        return materielRepository.save(materiel);
    }
    private String genererImage(Categorie categorie) {
        switch (categorie) {
            case ORDINATEUR:
                return "https://m.media-amazon.com/images/I/71gK5omFfyL._AC_UL320_.jpg";
            case IMPRIMANTE:
                return "https://m.media-amazon.com/images/I/61qRYtzf8WL._AC_SR180,120_QL70_.jpg";
            case SERVEUR:
                return "https://m.media-amazon.com/images/I/51dPXEYwNLL._AC_UL320_.jpg";
            case CABLE_ETHENET:
                return "https://m.media-amazon.com/images/I/61CD1I5wjbL._AC_SR480%2C570_.jpg";
            case CABLE_HDMI:
                return "https://m.media-amazon.com/images/I/816qsbv4dbL._AC_UY218_.jpg";

            case CABLE_VGA:
                return "https://m.media-amazon.com/images/I/71UF3-LqDCL._AC_SR480%2C570_.jpg";
            case MULTIPRISE:
                return "https://m.media-amazon.com/images/I/61mKjCKenBL._AC_UL320_.jpg";
            case VIDEO_PROJECTEUR:
                return "https://m.media-amazon.com/images/I/51lrImTm8nL._AC_UL320_.jpg";
            default:
                return "https://www.amazon.fr/Informatique-accessoires/b?ie=UTF8&node=427942031";
        }
    }

    public Materiel modifierMateriel(Long id, Materiel materiel) {
        materiel.setNom(materiel.getNom());
        materiel.setDescription(materiel.getDescription());
        materiel.setEtat(materiel.getEtat());
        materiel.setDisponible(materiel.isDisponible());
        return materielRepository.save(materiel);
    }

    public void supprimerMateriel(Long id) {
        materielRepository.deleteById(id);
    }

    public List<Materiel> listerTous() {return materielRepository.findAll();
    }


    public Materiel getMaterielParId(Long materielId) {
        return materielRepository.findById(materielId)
                .orElseThrow(() -> new RuntimeException("Matériel non trouvé avec l'ID : " + materielId));
    }
}
