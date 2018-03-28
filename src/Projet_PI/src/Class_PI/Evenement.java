package Class_PI;

import java.util.*;

class Evenement {
  private String nom;
  private int nbParticipantRequis;
  private String description;
  private String srcImage;
  private Adresse adresseEve;

  private ArrayList<Plage> listePlage;
  private LinkedList<Commentaire> listeCommentaire;
  private LinkedList<Contact> listeContact;
  private ArrayList<Section> listeSection;
  
	public Evenement(String nom, int nbParticipantRequis, String description, String srcImage, Adresse adresseEve)
	{
		super();
		this.nom = nom;
		this.nbParticipantRequis = nbParticipantRequis;
		this.description = description;
		this.srcImage = srcImage;
		this.adresseEve = adresseEve;
	}
}
