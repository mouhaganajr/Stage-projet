package uasz.sn.stage.Gestion_Materiels.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.stage.Gestion_Ufr.modele.Ufr;
import uasz.sn.stage.Reservation.Modele.ReservationModele;
import uasz.sn.stage.Utilisateur.model.ResponsableUFR;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Materiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;

    @Enumerated(EnumType.STRING) // Pour stocker l'enum sous forme de chaîne dans la base de données
    private Categorie categorie;

    private String etat;
    private boolean disponible;
    private String image;

    @ManyToOne
    @JoinColumn(name = "ufr_id")
    private Ufr ufr;

    @OneToMany(mappedBy = "materiel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReservationModele> reservations;

//    @ManyToOne
//    @JoinColumn(name = "chef_id", nullable = false)
//    private ResponsableUFR responsable;
}
