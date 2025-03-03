package uasz.sn.stage.Utilisateur.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uasz.sn.stage.Authentification.modele.Utilisateur;

@Entity
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Etudiant extends Utilisateur{

    private String ufr; // Unité de Formation et de Recherche
    private String matricule; // Identifiant étudiant
    private String filiere;
}
