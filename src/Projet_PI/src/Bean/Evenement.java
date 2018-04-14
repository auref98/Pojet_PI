/*
 * Haute �cole Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integr�: r�alisation d'un logiciel de gestion des inscriptions � des �v�nements
 * 
 * Groupe: NamingException {
 * 				Adam Ludovic;
 *				Arnould Killian;
 * 				De Bernardi Christophe;
 * 				Fockedey Aurelien;
 * 				Mathieu Robin;
 * 				Modave Louis;
 * 				}
 */

package Bean;

import java.io.Serializable;
import java.util.*;

/**
 * Classe de type "bean" repr�sentant un �v�nement.<br><br>
 * Elle renseigne le nombre de participants requis et les sections repr�sent�es, l'adresse de l'�v�nement,
 * une r�f�rence vers les plages horaires n�cessaires ainsi que la liste des commentaires ayant �t� post�s par les participants � la suite de l'�v�nement.
 * @see Commentaire
 * @see Contact
 * @see Plage
 * @see Section
 * @see java.util.ArrayList
 * @see java.util.LinkedList
 */
public class Evenement implements Serializable{
	
	private int id;													// Attribut permettant de recuperer l'id r�f�ren�ant cette adresse dans la base de donnees			
	private String nom;												// Le nom de l'�v�nement
	private int nbParticipantsRequis;									// Le nombre total de participants requis pour repr�senter l'�cole lors de l'�v�nement, toutes sections et �tudiants et professeurs confondus
	private String description;											// Une br�ve description de l'�v�nement � l'attention des personnes int�r�ss�es
	private String image;												// Un lien vers une image / logo repr�sentant l'�v�nement
	private Adresse adresseEve;										// L'adresse de l'�v�nement

	private ArrayList<Plage> listePlage;									// La liste des plages horaires li�es � l'�v�nement (Les Plage font le lien entre l'�v�nement, les participants et les horaires)
	private LinkedList<Commentaire> listeCommentaire;					// La liste des commentaires reccueillis � la suite de l'�v�nement
	private LinkedList<Contact> listeContact;							// 
	private ArrayList<Section> listeSection;								// La liste des sections repr�sent�es � l'�v�nement
  
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/** Constructeur sans param�tre. */
	public Evenement() {}
	
	/**
	 * Constructeur initialisant tous les param�tres.<br><br>
	 * Pr�condition: tous les param�tres sont correctement initialis�s.<br>
	 * Postcondition: l'objet est initialis�; les attributs <code>id, nom, nbParticipantsRequis, description, image</code> et <code>adresseEve</code>" sont initialis�s avec la valeur du param�tre de m�me nom;
	 * les champs <code>listePlage, listeCommentaire, listeContact</code> et <code>listeSection</code> sont initialis�s avec une liste vide de type appropri�.<br>
	 * @param id l'identifiant (BD) de l'�v�nement
	 * @param nom le nom de l'�v�nement
	 * @param nbParticipantRequis le nombre total de participants requis pour repr�senter l'�cole lors de l'�v�nement
	 * @param description une br�ve description de l'�v�nement � l'attention des personnes int�r�ss�es
	 * @param image un lien vers une image / logo repr�sentant l'�v�nement
	 * @param adresseEve la r�f�rence de l'<code>Adresse</code> � laquelle se d�roule l'�v�nement
	 */
	public Evenement(int id, String nom, int nbParticipantRequis, String description, String image, Adresse adresseEve)
	{
		this.id = id;
		this.nom = nom;
		this.nbParticipantsRequis = nbParticipantRequis;
		this.description = description;
		this.image = image;
		this.adresseEve = adresseEve;
		
		// Initialisation des listes vides
		listePlage = new  ArrayList<Plage>();
		listeCommentaire = new LinkedList<Commentaire>();
		listeContact = new LinkedList<Contact>();
		listeSection = new ArrayList<Section>();
	}
	
	/**
	 * @author Aurelien
	 * @return clone de l'evenement
	 */
	/*public Evenement clone(){
		return new Evenement(this.nom, this.nbParticipantRequis, this.description,this.image,this.adresseEve.clone());
	}
	*/
	/**
	 * Compare un objet <code>java.lang.String</code> pass� en param�tre avec  le nom de l'objet courant. 
	 * Pr�condition: l'objet courant est initialis�.
	 * Postcondition: l'objet courant est inchang�.
	 * @author Aurelien
	 * @param nom le nom de l'�v�nement � comparer
	 * @return <code>true</code> si "nom" est �gal au nom de l'�v�nement, d'apr�s la d�finition de la m�thode equals(String) de la classe String; <code>false</code> sinon
	 */
	public boolean equals(String nom){
		return nom.equals(this.nom);
	}

//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################
	
// Getter et setter pour l'attribut "id"	

	/**
	 * Renvoie l'identifiant (BD) de l'objet.
	 * @return l'identifiant (BD) de l'objet
	 */
	public int getId() {
		return id;
	}

	/**
	 * Affecte  l'identifiant (BD) de l'objet.
	 * @param id l'identifiant (BD) de l'objet
	 */
	public void setId(int id) {
		this.id = id;
	}
	
// Getter et setter pour l'attribut "nom"	
	
	/**
	 * Renvoie le nom de l'�v�nement.
	 * @return le nom de l'�v�nement
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Affecte le nom de l'�v�nement.
	 * @param nom le nom de l'�v�nement
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

// Getter et setter pour l'attribut "nbParticipantsRequis"	
	
	/**
	 * Renvoie le nombre de participants requis pour cet �v�nement.
	 * @return le nombre de participants requis
	 */
	public int getNbParticipantRequis() {
		return nbParticipantsRequis;
	}

	/**
	 * Affecte le nombre de participants requis pour cet �v�nement.
	 * @param nbParticipantRequis le nombre de participants requis pour cet �v�nement
	 */
	public void setNbParticipantRequis(int nbParticipantRequis) {
		this.nbParticipantsRequis = nbParticipantRequis;
	}

// Getter et setter pour l'attribut "description"	
	
	/**
	 * Renvoie la description de l'�v�nement.
	 * @return la description de l'�v�nement
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Affecte la description de l'�v�nement.
	 * @param description la description de l'�v�nement.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

// Getter et setter pour l'attribut "image"	
	
	/**
	 * Renvoie le lien vers l'image correspondant � cet �v�nement.
	 * @return le lien vers l'image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Affecte le lien vers l'image correspondant � cet �v�nement.
	 * @param srcImage le lien vers l'image � affecter
	 */
	public void setImage(String srcImage) {
		this.image = image;
	}

// Getter et setter pour l'attribut "adresseEve"	
	
	/**
	 * Renvoie l'adresse de l'�v�nement.
	 * @return un objet de type <code>Adresse</code> repr�sentant l'adresse de cet �v�nement
	 * @see Adresse
	 */
	public Adresse getAdresseEve() {
		return adresseEve;
	}

	/**
	 * Affecte l'adresse de l'�v�nement.
	 * @param adresseEve un objet de type <code> Adresse</code> repr�sentant l'adresse de cet �v�nement
	 * @see Adresse
	 */
	public void setAdresseEve(Adresse adresseEve) {
		this.adresseEve = adresseEve;
	}
//###################################################################################################################################################################
	
	// Manipulation des listes
	
//###################################################################################################################################################################
	
	public void addPlage(Plage p) {
		listePlage.add(p);
	}
	public void addCommentaire(Commentaire c) {
		listeCommentaire.add(c);
	}
	public void addContact(Contact c) {
		listeContact.add(c);
	}
	public void addSection(Section s) {
		listeSection.add(s);
	}
	
	public void delPlage(Plage p) {
		listePlage.remove(p);
	}
	public void delCommentaire(Commentaire c) {
		listeCommentaire.remove(c);
	}
	public void delContact(Contact c) {
		listeContact.remove(c);
	}
	public void delSection(Section s) {
		listeSection.remove(s);
	}
	
	public void setPlage(ArrayList<Plage> p) {
		listePlage = p;
	}
	public void setCommentaire(LinkedList<Commentaire> c) {
		listeCommentaire = c;
	}
	public void setContact(LinkedList<Contact> c) {
		listeContact = c;
	}
	public void setSection(ArrayList<Plage> p) {
		listePlage = p;
	}
	
	public ArrayList<Plage> getPlage() {
		return listePlage;
	}
	public LinkedList<Commentaire> getCommentaire() {
		return listeCommentaire;
	}
	public LinkedList<Contact> getContact() {
		return listeContact;
	}
	public ArrayList<Plage> getSection() {
		return listePlage;
	}
}
