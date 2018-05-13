/*
 * Haute �cole Robert Schuman - Libramont, ann�e scolaire 2017 - 2018
 * Bachelier en informatique de gestion, bloc 2	
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
 * Classe de type "bean" repr�sentant un �v�nement. <br><br>
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
	 * <pre>
	 * Constructeur initialisant tous les param�tres.
	 * Pr�condition:		tous les param�tres sont correctement initialis�s. 
	 * Postcondition: 	l'objet est initialis�; les attributs <code>id, nom, nbParticipantsRequis, description, image</code> et <code>adresseEve</code> sont initialis�s avec la valeur du param�tre de m�me nom;
	 * 			les champs <code>listePlage, listeCommentaire, listeContact</code> et <code>listeSection</code> sont initialis�s avec une liste vide de type appropri�. 
	 * </pre>
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
	 * Compare un objet <code>java.lang.String</code> pass� en param�tre avec  l'id de l'objet courant. 
	 * Pr�condition: l'objet courant est initialis�.
	 * Postcondition: l'objet courant est inchang�.
	 * @author Aurelien
	 * @param id l'id de l'�v�nement � comparer
	 * @return <code>true</code> si <code>id</code> est �gal � l'id de l'�v�nement; <code>false</code> sinon
	 */
	public boolean equals(int id){
		return id == this.id;
	}

//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################
	
// Getter et setter pour l'attribut "id"	

	/**
	 * Renvoie l'identifiant (BD) de l'�v�nement.
	 * @return l'identifiant (BD) de l'�v�nement
	 */
	public int getId() {
		return id;
	}

	/**
	 * Affecte  l'identifiant (BD) de l'�v�nement.
	 * @param id l'identifiant (BD) de l'�v�nement
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
	public int getNbParticipantsRequis() {
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
		this.image = srcImage;
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

// Getter et setter pour l'attribut "listePlage"		
	
	/**
	 * Renvoie la r�f�rence de la liste des objets <code>Plage</code> li�s � cet �v�nement.
	 * @return la r�f�rence de la liste des objets <code>Plage</code> li�s � cet �v�nement
	 * @see Plage
	 * @see java.util.ArrayList
	 */
	public ArrayList<Plage> getListePlage() {
		return listePlage;
	}
	
	/**
	 * Affecte la r�f�rence d'une liste de <code>Plage</code>.
	 * @param p la r�f�rence d'une liste de <code>Plage</code> � affecter
	 * @see Plage
	 * @see java.util.ArrayList
	 */
	public void setListPlage(ArrayList<Plage> p) {
		listePlage = p;
	}
	
// Getter et setter pour l'attribut "listeCommentaire"	
	
	/**
	 * Renvoie la r�f�rence de la liste des objets <code>Commentaire</code> li�s � cet �v�nement.
	 * @return la r�f�rence de la liste des <code>Commentaire</code> li�s � cet �v�nement
	 * @see Commentaire
	 * @see java.util.LinkedList
	 */
	public LinkedList<Commentaire> getListeCommentaire() {
		return listeCommentaire;
	}
	
	/**
	 * Affecte la r�f�rence d'une liste de <code>Commentaire</code>.
	 * @param c la r�f�rence d'une liste de <code>Commentaire</code> � affecter
	 * @see Commentaire
	 * @see java.util.LinkedList
	 */
	public void setCommentaire(LinkedList<Commentaire> c) {
		listeCommentaire = c;
	}
	
// Getter et setter pour l'attribut "listeContact"	
	
	/**
	 * Renvoie la r�f�rence de la liste des objets <code>Contact</code> li�s � cet �v�nement.
	 * @return la r�f�rence de la liste des <code>Contact</code> li�s � cet �v�nement
	 * @see Contact
	 * @see java.util.LinkedList
	 */
	public LinkedList<Contact> getContact() {
		return listeContact;
	}
	
	/**
	 * Affecte la r�f�rence d'une liste de <code>Contact</code>.
	 * @param c la r�f�rence d'une liste de <code>Contact</code> � affecter
	 * @see Contact
	 * @see java.util.LinkedList
	 */
	public void setContact(LinkedList<Contact> c) {
		listeContact = c;
	}	
	
// Getter et setter pour l'attribut "listeSection"
	
	/**
	 * Renvoie la r�f�rence de la liste des objets <code>Section</code> li�s � cet �v�nement.
	 * @return la r�f�rence de la liste des <code>Section</code> li�s � cet �v�nement
	 * @see Section
	 * @see java.util.ArrayList
	 */
	public ArrayList<Section> getListeSection() {
		return listeSection;
	}
	
	/**
	 * Affecte la r�f�rence d'une liste de <code>Section</code>.
	 * @param s la r�f�rence d'une liste de <code>Section</code> � affecter
	 * @see Section
	 * @see java.util.ArrayList
	 */
	public void setSection(ArrayList<Section> s) {
		listeSection = s;
	}
	
//###################################################################################################################################################################
	
	// Manipulation des listes
	
//###################################################################################################################################################################
	
// M�thodes add (ajouter un abjet � une liste)
	
	/**
	 * Ajoute un objet <code>Plage</code> � l'objet courant. <br><br>
	 * Pr�condition: l'attribut "<code>listePlage</code>" est initialis� avec un type valide (<code>Plage</code>); l'objet <code>Plage p</code> est correctement initialis�. <br>
	 * Postcondition: l'objet <code>Plage p</code> a �t� ajout� � l'objet courant. <br>
	 * @param p l'objet <code>Plage</code> � ajouter � l'objet courant
	 * @see Plage
	 * @see java.util.ArrayList#add(Object o)
	 */
	public void addPlage(Plage p) {
		listePlage.add(p);
	}
	
	/**
	 * Ajoute un objet <code>Commentaire</code> � l'objet courant. <br><br>
	 * Pr�condition: l'attribut "<code>listeCommentaire</code>" est initialis� avec un type valide (<code>Commentaire</code>); l'objet <code>Commentaire s</code> est correctement initialis�. <br>
	 * Postcondition: l'objet <code>Commentaire c</code> a �t� ajout� � l'objet courant. <br>
	 * @param c l'objet <code>Commentaire</code> � ajouter � l'objet courant
	 * @see Commentaire
	 * @see LinkedList#add(Object o)
	 */
	public void addCommentaire(Commentaire c) {
		listeCommentaire.add(c);
	}
	
	/**
	 * Ajoute un objet <code>Contact</code> � l'objet courant. <br><br>
	 * Pr�condition: l'attribut "<code>listeContact</code>" est initialis� avec un type valide (<code>Contact</code>); l'objet <code>Contact s</code> est correctement initialis�. <br>
	 * Postcondition: l'objet <code>Contact c</code> a �t� ajout� � l'objet courant. <br>
	 * @param c l'objet <code>Contact</code> � ajouter � l'objet courant
	 * @see Contact
	 * @see java.util.LinkedList#add(Object o)
	 */
	public void addContact(Contact c) {
		listeContact.add(c);
	}
	
	/**
	 * Ajoute un objet <code>Section</code> � l'objet courant. <br><br>
	 * Pr�condition: l'attribut "<code>listeSection</code>" est initialis� avec un type valide (<code>Section</code>); l'objet <code>Section s</code> est correctement initialis�. <br>
	 * Postcondition: l'objet <code>Section s</code> a �t� ajout� � l'objet courant. <br>
	 * @param s l'objet <code>Section</code> � ajouter � l'objet courant
	 * @see Section
	 * @see java.util.ArrayList#add(Object o)
	 */
	public void addSection(Section s) {
		listeSection.add(s);
	}
	
// Methodes del (Supprimer un objet d'une liste)
	
	/**
	 * Supprime un objet <code>Plage</code> de l'objet courant. <br><br>
	 * Pr�condition: l'attribut "<code>listePlage</code>" est initialis�. <br>
	 * Postcondition: si l'objet <code>Plage p</code> se trouvait dans l'objet courant, il en a �t� supprim�; sinon l'objet courant est inchang�. <br>
	 * @param p l'objet <code>Plage</code> � supprimer de l'objet courant
	 * @see Plage
	 * @see java.util.ArrayList#remove(Object o)
	 */
	public void delPlage(Plage p) {
		listePlage.remove(p);
	}
	
	/**
	 * Supprime un objet <code>Commentaire</code> de l'objet courant. <br><br>
	 * Pr�condition: l'attribut "<code>listeCommentaire</code>" est initialis�. <br>
	 * Postcondition: si l'objet <code>Commentaire c</code> se trouvait dans l'objet courant, il en a �t� supprim�; sinon l'objet courant est inchang�. <br>
	 * @param c l'objet <code>Commentaire</code> � supprimer de l'objet courant
	 * @see Commentaire
	 * @see java.util.LinkedList#remove(Object o)
	 */
	public void delCommentaire(Commentaire c) {
		listeCommentaire.remove(c);
	}
	
	/**
	 * Supprime un objet <code>Contact</code> de l'objet courant. <br><br>
	 * Pr�condition: l'attribut "<code>listeContact</code>" est initialis�. <br>
	 * Postcondition: si l'objet <code>Contact c</code> se trouvait dans l'objet courant, il en a �t� supprim�; sinon l'objet courant est inchang�. <br>
	 * @param c l'objet <code>Contact</code> � supprimer de l'objet courant
	 * @see Contact
	 * @see java.util.LinkedList#remove(Object o)
	 */
	public void delContact(Contact c) {
		listeContact.remove(c);
	}
	
	/**
	 * Supprime un objet <code>Section</code> de l'objet courant. <br><br>
	 * Pr�condition: l'attribut "<code>listeSection</code>" est initialis�. <br>
	 * Postcondition: si l'objet <code>Section s</code> se trouvait dans l'objet courant, il en a �t� supprim�; sinon l'objet courant est inchang�. <br>
	 * @param s l'objet <code>Section</code> � supprimer de l'objet courant
	 * @see Section
	 * @see java.util.ArrayList#remove(Object o)
	 */
	public void delSection(Section s) {
		listeSection.remove(s);
	}
}
	
