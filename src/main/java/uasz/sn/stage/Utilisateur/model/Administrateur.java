package uasz.sn.stage.Utilisateur.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.JOINED) // Permet l'héritage en base de données
public abstract class Administrateur extends Utilisateur {
    private String fonction;
}
