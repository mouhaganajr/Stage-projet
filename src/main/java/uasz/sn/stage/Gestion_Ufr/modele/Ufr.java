package uasz.sn.stage.Gestion_Ufr.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.stage.Gestion_Materiels.modele.Materiel;
import java.util.List;

@Entity
@Table(name = "ufrs") // Bonne pratique pour nommer explicitement la table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ufr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;

    private String description;

    @OneToMany(mappedBy = "ufr", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Materiel> materiels; // Une liste pour la relation OneToMany
}
