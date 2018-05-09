/*
 * Haute école Robert Schuman - Libramont, année scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integré: réalisation d'un logiciel de gestion des inscriptions à des événements
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
 * Classe de type "bean" représentant un événement. <br><br>
 * Elle renseigne le nombre de participants requis et les sections représentées, l'adresse de l'événement,
 * une référence vers les plages horaires nécessaires ainsi que la liste des commentaires ayant été postés par les participants à la suite de l'événement.
 * @see Commentaire
 * @see Contact
 * @see Plage
 * @see Section
 * @see java.util.ArrayList
 * @see java.util.LinkedList
 */
public class Evenement implements Serializable{
	
	private int id;													// Attribut permettant de recuperer l'id référençant cette adresse dans la base de donnees			
	private String nom;												// Le nom de l'événement
	private int nbParticipantsRequis;									// Le nombre total de participants requis pour représenter l'école lors de l'événement, toutes sections et étudiants et professeurs confondus
	private String description;											// Une brève description de l'événement à l'attention des personnes intéréssées
	private String image;												// Un lien vers une image / logo représentant l'événement
	private Adresse adresseEve;										// L'adresse de l'événement

	private ArrayList<Plage> listePlage;									// La liste des plages horaires liées à l'événement (Les Plage font le lien entre l'événement, les participants et les horaires)
	private LinkedList<Commentaire> listeCommentaire;					// La liste des commentaires reccueillis à la suite de l'événement
	private LinkedList<Contact> listeContact;							// 
	private ArrayList<Section> listeSection;								// La liste des sections représentées à l'événement
  
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/** Constructeur sans paramètre. */
	public Evenement() {}
	
	/**
	 * <pre>
	 * Constructeur initialisant tous les paramètres.
	 * Précondition:		tous les paramètres sont correctement initialisés. 
	 * Postcondition: 	l'objet est initialisé; les attributs <code>id, nom, nbParticipantsRequis, description, image</code> et <code>adresseEve</code> sont initialisés avec la valeur du paramètre de même nom;
	 * 			les champs <code>listePlage, listeCommentaire, listeContact</code> et <code>listeSection</code> sont initialisés avec une liste vide de type approprié. 
	 * </pre>
	 * @param id l'identifiant (BD) de l'événement
	 * @param nom le nom de l'événement
	 * @param nbParticipantRequis le nombre total de participants requis pour représenter l'école lors de l'événement
	 * @param description une brève description de l'événement à l'attention des personnes intéréssées
	 * @param image un lien vers une image / logo représentant l'événement
	 * @param adresseEve la référence de l'<code>Adresse</code> à laquelle se déroule l'événement
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
	 * Compare un objet <code>java.lang.String</code> passé en paramètre avec  le nom de l'objet courant. 
	 * Précondition: l'objet courant est initialisé.
	 * Postcondition: l'objet courant est inchangé.
	 * @author Aurelien
	 * @param nom le nom de l'événement à comparer
	 * @return <code>true</code> si "nom" est égal au nom de l'évènement, d'après la définition de la méthode equals(String) de la classe String; <code>false</code> sinon
	 */
	public boolean equals(int id){
		return id == this.id;
	}

//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################
	
// Getter et setter pour l'attribut "id"	

	/**
	 * Renvoie l'identifiant (BD) de l'événement.
	 * @return l'identifiant (BD) de l'événement
	 */
	public int getId() {
		return id;
	}

	/**
	 * Affecte  l'identifiant (BD) de l'événement.
	 * @param id l'identifiant (BD) de l'événement
	 */
	public void setId(int id) {
		this.id = id;
	}
	
// Getter et setter pour l'attribut "nom"	
	
	/**
	 * Renvoie le nom de l'événement.
	 * @return le nom de l'événement
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Affecte le nom de l'événement.
	 * @param nom le nom de l'événement
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

// Getter et setter pour l'attribut "nbParticipantsRequis"	
	
	/**
	 * Renvoie le nombre de participants requis pour cet événement.
	 * @return le nombre de participants requis
	 */
	public int getNbParticipantsRequis() {
		return nbParticipantsRequis;
	}

	/**
	 * Affecte le nombre de participants requis pour cet événement.
	 * @param nbParticipantRequis le nombre de participants requis pour cet événement
	 */
	public void setNbParticipantRequis(int nbParticipantRequis) {
		this.nbParticipantsRequis = nbParticipantRequis;
	}

// Getter et setter pour l'attribut "description"	
	
	/**
	 * Renvoie la description de l'événement.
	 * @return la description de l'événement
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Affecte la description de l'événement.
	 * @param description la description de l'événement.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

// Getter et setter pour l'attribut "image"	
	
	/**
	 * Renvoie le lien vers l'image correspondant à cet événement.
	 * @return le lien vers l'image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Affecte le lien vers l'image correspondant à cet événement.
	 * @param srcImage le lien vers l'image à affecter
	 */
	public void setImage(String srcImage) {
		this.image = srcImage;
	}

// Getter et setter pour l'attribut "adresseEve"	
	
	/**
	 * Renvoie l'adresse de l'événement.
	 * @return un objet de type <code>Adresse</code> représentant l'adresse de cet événement
	 * @see Adresse
	 */
	public Adresse getAdresseEve() {
		return adresseEve;
	}

	/**
	 * Affecte l'adresse de l'événement.
	 * @param adresseEve un objet de type <code> Adresse</code> représentant l'adresse de cet événement
	 * @see Adresse
	 */
	public void setAdresseEve(Adresse adresseEve) {
		this.adresseEve = adresseEve;
	}

// Getter et setter pour l'attribut "listePlage"		
	
	/**
	 * Renvoie la référence de la liste des objets <code>Plage</code> liés à cet événement.
	 * @return la référence de la liste des objets <code>Plage</code> liés à cet événement
	 * @see Plage
	 * @see java.util.ArrayList
	 */
	public ArrayList<Plage> getListePlage() {
		return listePlage;
	}
	
	/**
	 * Affecte la référence d'une liste de <code>Plage</code>.
	 * @param p la référence d'une liste de <code>Plage</code> à affecter
	 * @see Plage
	 * @see java.util.ArrayList
	 */
	public void setListPlage(ArrayList<Plage> p) {
		listePlage = p;
	}
	
// Getter et setter pour l'attribut "listeCommentaire"	
	
	/**
	 * Renvoie la référence de la liste des objets <code>Commentaire</code> liés à cet événement.
	 * @return la référence de la liste des <code>Commentaire</code> liés à cet événement
	 * @see Commentaire
	 * @see java.util.LinkedList
	 */
	public LinkedList<Commentaire> getListeCommentaire() {
		return listeCommentaire;
	}
	
	/**
	 * Affecte la référence d'une liste de <code>Commentaire</code>.
	 * @param c la référence d'une liste de <code>Commentaire</code> à affecter
	 * @see Commentaire
	 * @see java.util.LinkedList
	 */
	public void setCommentaire(LinkedList<Commentaire> c) {
		listeCommentaire = c;
	}
	
// Getter et setter pour l'attribut "listeContact"	
	
	/**
	 * Renvoie la référence de la liste des objets <code>Contact</code> liés à cet événement.
	 * @return la référence de la liste des <code>Contact</code> liés à cet événement
	 * @see Contact
	 * @see java.util.LinkedList
	 */
	public LinkedList<Contact> getContact() {
		return listeContact;
	}
	
	/**
	 * Affecte la référence d'une liste de <code>Contact</code>.
	 * @param c la référence d'une liste de <code>Contact</code> à affecter
	 * @see Contact
	 * @see java.util.LinkedList
	 */
	public void setContact(LinkedList<Contact> c) {
		listeContact = c;
	}	
	
// Getter et setter pour l'attribut "listeSection"
	
	/**
	 * Renvoie la référence de la liste des objets <code>Section</code> liés à cet événement.
	 * @return la référence de la liste des <code>Section</code> liés à cet événement
	 * @see Section
	 * @see java.util.ArrayList
	 */
	public ArrayList<Section> getListeSection() {
		return listeSection;
	}
	
	/**
	 * Affecte la référence d'une liste de <code>Section</code>.
	 * @param s la référence d'une liste de <code>Section</code> à affecter
	 * @see Section
	 * @see java.util.ArrayList
	 */
	public void setSection(ArrayList<Section> s) {
		listeSection = s;
	}
	
//###################################################################################################################################################################
	
	// Manipulation des listes
	
//###################################################################################################################################################################
	
// Méthodes add (ajouter un abjet à une liste)
	
	/**
	 * Ajoute un objet <code>Plage</code> à l'objet courant. <br><br>
	 * Précondition: l'attribut "<code>listePlage</code>" est initialisé avec un type valide (<code>Plage</code>); l'objet <code>Plage p</code> est correctement initialisé. <br>
	 * Postcondition: l'objet <code>Plage p</code> a été ajouté à l'objet courant. <br>
	 * @param p l'objet <code>Plage</code> à ajouter à l'objet courant
	 * @see Plage
	 * @see java.util.ArrayList#add(Object o)
	 */
	public void addPlage(Plage p) {
		listePlage.add(p);
	}
	
	/**
	 * Ajoute un objet <code>Commentaire</code> à l'objet courant. <br><br>
	 * Précondition: l'attribut "<code>listeCommentaire</code>" est initialisé avec un type valide (<code>Commentaire</code>); l'objet <code>Commentaire s</code> est correctement initialisé. <br>
	 * Postcondition: l'objet <code>Commentaire c</code> a été ajouté à l'objet courant. <br>
	 * @param c l'objet <code>Commentaire</code> à ajouter à l'objet courant
	 * @see Commentaire
	 * @see LinkedList#add(Object o)
	 */
	public void addCommentaire(Commentaire c) {
		listeCommentaire.add(c);
	}
	
	/**
	 * Ajoute un objet <code>Contact</code> à l'objet courant. <br><br>
	 * Précondition: l'attribut "<code>listeContact</code>" est initialisé avec un type valide (<code>Contact</code>); l'objet <code>Contact s</code> est correctement initialisé. <br>
	 * Postcondition: l'objet <code>Contact c</code> a été ajouté à l'objet courant. <br>
	 * @param c l'objet <code>Contact</code> à ajouter à l'objet courant
	 * @see Contact
	 * @see java.util.LinkedList#add(Object o)
	 */
	public void addContact(Contact c) {
		listeContact.add(c);
	}
	
	/**
	 * Ajoute un objet <code>Section</code> à l'objet courant. <br><br>
	 * Précondition: l'attribut "<code>listeSection</code>" est initialisé avec un type valide (<code>Section</code>); l'objet <code>Section s</code> est correctement initialisé. <br>
	 * Postcondition: l'objet <code>Section s</code> a été ajouté à l'objet courant. <br>
	 * @param s l'objet <code>Section</code> à ajouter à l'objet courant
	 * @see Section
	 * @see java.util.ArrayList#add(Object o)
	 */
	public void addSection(Section s) {
		listeSection.add(s);
	}
	
// Methodes del (Supprimer un objet d'une liste)
	
	/**
	 * Supprime un objet <code>Plage</code> de l'objet courant. <br><br>
	 * Précondition: l'attribut "<code>listePlage</code>" est initialisé. <br>
	 * Postcondition: si l'objet <code>Plage p</code> se trouvait dans l'objet courant, il en a été supprimé; sinon l'objet courant est inchangé. <br>
	 * @param p l'objet <code>Plage</code> à supprimer de l'objet courant
	 * @see Plage
	 * @see java.util.ArrayList#remove(Object o)
	 */
	public void delPlage(Plage p) {
		listePlage.remove(p);
	}
	
	/**
	 * Supprime un objet <code>Commentaire</code> de l'objet courant. <br><br>
	 * Précondition: l'attribut "<code>listeCommentaire</code>" est initialisé. <br>
	 * Postcondition: si l'objet <code>Commentaire c</code> se trouvait dans l'objet courant, il en a été supprimé; sinon l'objet courant est inchangé. <br>
	 * @param c l'objet <code>Commentaire</code> à supprimer de l'objet courant
	 * @see Commentaire
	 * @see java.util.LinkedList#remove(Object o)
	 */
	public void delCommentaire(Commentaire c) {
		listeCommentaire.remove(c);
	}
	
	/**
	 * Supprime un objet <code>Contact</code> de l'objet courant. <br><br>
	 * Précondition: l'attribut "<code>listeContact</code>" est initialisé. <br>
	 * Postcondition: si l'objet <code>Contact c</code> se trouvait dans l'objet courant, il en a été supprimé; sinon l'objet courant est inchangé. <br>
	 * @param c l'objet <code>Contact</code> à supprimer de l'objet courant
	 * @see Contact
	 * @see java.util.LinkedList#remove(Object o)
	 */
	public void delContact(Contact c) {
		listeContact.remove(c);
	}
	
	/**
	 * Supprime un objet <code>Section</code> de l'objet courant. <br><br>
	 * Précondition: l'attribut "<code>listeSection</code>" est initialisé. <br>
	 * Postcondition: si l'objet <code>Section s</code> se trouvait dans l'objet courant, il en a été supprimé; sinon l'objet courant est inchangé. <br>
	 * @param s l'objet <code>Section</code> à supprimer de l'objet courant
	 * @see Section
	 * @see java.util.ArrayList#remove(Object o)
	 */
	public void delSection(Section s) {
		listeSection.remove(s);
	}
}
	
