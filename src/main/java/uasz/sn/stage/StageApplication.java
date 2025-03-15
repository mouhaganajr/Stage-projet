package uasz.sn.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uasz.sn.stage.Authentification.modele.Role;
import uasz.sn.stage.Authentification.service.UtilisateurService;
import uasz.sn.stage.Gestion_Materiels.modele.Categorie;
import uasz.sn.stage.Gestion_Materiels.modele.Materiel;
import uasz.sn.stage.Gestion_Materiels.service.MaterielService;
import uasz.sn.stage.Gestion_Ufr.modele.Ufr;
import uasz.sn.stage.Gestion_Ufr.service.UfrService;
import uasz.sn.stage.Utilisateur.model.Administrateur;
import uasz.sn.stage.Utilisateur.model.Chef;
import uasz.sn.stage.Utilisateur.model.Etudiant;
import uasz.sn.stage.Utilisateur.model.ResponsableUFR;
import uasz.sn.stage.Utilisateur.repository.ResponsableUFRRepository;
import uasz.sn.stage.Utilisateur.service.AdministrateurService;
import uasz.sn.stage.Utilisateur.service.EtudiantService;
import uasz.sn.stage.Utilisateur.service.ResponsableUFRService;

import java.util.Date;

@SpringBootApplication
public class StageApplication implements CommandLineRunner {

    @Autowired
    private UfrService ufrService;
    @Autowired
    private MaterielService materielService;

	public static void main(String[] args) {
		SpringApplication.run(StageApplication.class, args);
	}


	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private AdministrateurService administrateurService;
	@Autowired
	private ResponsableUFRService responsableUFRService;
	@Autowired
	private EtudiantService etudiantService;



	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	public void run(String... args) throws Exception {
		Role admin = utilisateurService.ajouter_Role(new Role("ADMIN"));
		Role responsable = utilisateurService.ajouter_Role(new Role("RESPONSABLE_UFR"));
		Role etudiant = utilisateurService.ajouter_Role(new Role("ETUDIANT"));

		String password = passwordEncoder().encode("Passer123");


		Chef user = new Chef();
		user.setNom("Gueye"); user.setPrenom("Gana"); user.setUsername("gueye@gmail.com");
		user.setPassword(password); user.setDateCreation(new Date()); user.setActive(true);
		user.setFonction("Administrateur");
		administrateurService.ajouterAdministrateur(user);
		utilisateurService.ajouter_UtilisateurRoles(user, admin);


		ResponsableUFR user1 = new ResponsableUFR();
		user1.setNom("Sow"); user1.setPrenom("Abou"); user1.setUsername("abou@gmail.com");
		user1.setPassword(password); user1.setDateCreation(new Date()); user1.setActive(true);
		user1.setFonction("Responsable UFR");
		user1.setUfr("ST");
		responsableUFRService.ajouterResponsableUFR(user1);
		utilisateurService.ajouter_UtilisateurRoles(user1, responsable);


		Etudiant user3 = new Etudiant();
		user3.setNom("DIOUF"); user3.setPrenom("Ngone"); user3.setUsername("diouf@gmail.com");
		user3.setPassword(password); user3.setDateCreation(new Date()); user3.setActive(true);
		user3.setUfr("ST");
		etudiantService.ajouterEtudiant(user3);
		utilisateurService.ajouter_UtilisateurRoles(user3,etudiant);

		Ufr ufr= new Ufr();
		ufr.setNom("ST");
		ufrService.createUfr(ufr);

		Materiel materiel = new Materiel();
		materiel.setNom("Ordiateur");
		materiel.setEtat("new");
		materiel.setDescription("Ram 4");
		materiel.setCategorie(Categorie.ORDINATEUR);
		materielService.ajouterMateriel(materiel);



	}
}
