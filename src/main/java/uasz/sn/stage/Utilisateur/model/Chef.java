package uasz.sn.stage.Utilisateur.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Chef extends Administrateur {
    private String Grade; // Ex: Sciences et Technologies, Lettres...
}
