/*
 * Haute école Robert Schuman - Libramont, année scolaire 2017 - 2018
 * Bachelier en informatique de gestion, bloc 2
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

/**
 * Classe de type "bean" représentant une plage horaire. <br><br>
 * Elle renseigne la date associée à cette plage horaire et ses heures de début et de fin, ainsi que l'objet <code>Evenement</code> auquel elle se rapporte. <br>
 * elle ne représente pas l'événement lui-même mais une partie de celui-ci. Un <code>Evenement</code> est modélisé par un ensemble de <code>Plage</code> distinctes. <br>
 * Cette classe a été conçue afin de modéliser une plage horaire sur une seule journée; elle ne fonctionne pas pour représenter une durée s'étendant au delà de minuit. <br>
 * @see Evenement
 * @see Inscription
 * @see java.util.LinkedList
 * @see java.time.LocalDate
 * @see java.time.LocalTime
 */
public class Plage implements Serializable{
	
	private int id;													// Attribut permettant de récuperer l'id référençant cette adresse dans la base de données
	private LocalDate date;
	private LocalTime heureDebut;
	private LocalTime heureFin;
	
	private Evenement eve;											// Référence vers l'objet Evenement auquel se rapporte la plage horaire se rapporte
	private LinkedList<Inscription> listeInscription;						// Référence vers la liste des objets Inscription relatifs à cette plage horaire (Une Inscription lie une Plage à un objet Representant)
	
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/** Constructeur sans paramètre. */
	public Plage() {
		this.listeInscription = new LinkedList<Inscription>();
	}
	
	/**
	 * Constructeur initialisant tous les attributs de l'objet. <br><br>
	 * Précondition:  tous les paramètres sont correctement initialisés. <br>
	 * Postcondition: l'objet a été initialisé, tous ses champs (à l'exception de "<code>listeInscription</code>" sont initialisés avec la valeur du paramètre de même nom. <br>
	 * @param id l'identifiant (BD) de la plage
	 * @param date la date à laquelle la plage se rapporte. au format <code>java.time.LocalDate</code>
	 * @param heureDebut l'heure de début de la plage au format <code>java.time.LocalTime</code>
	 * @param heureFin l'heure de fin de la plage au format <code>java.time.LocalTime</code>
	 * @param eve la référence de l'Evenement auquel la plage se rapporte
	 */
	public Plage(int id, LocalDate date, LocalTime heureDebut, LocalTime heureFin, Evenement eve){
		this.id = id;
		this.date=date;
		this.heureDebut=heureDebut;
		this.heureFin=heureFin;
		this.eve = eve;
		this.listeInscription = new LinkedList<Inscription>();
  	}
	
//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################

// Getter et setter pour l'attribut "id"

  	/**
  	 * Renvoie l'identifiant (BD) de la plage.
	 * @return l'identifiant (BD) de la plage
	 */
	public int getId() {
		return id;
	}

	/**
	 * Affecte l'identifiant (BD) de la plage.
	 * @param id l'identifiant (BD) de la plage
	 */
	public void setId(int id) {
		this.id = id;
	}
	
// Getter et setter pour l'attribut "date"
	
	/**
	 * Renvoie la date à laquelle la plage se rapporte.
	 * @return la date à laquelle la plage se rapporte.
	 */
	public LocalDate getDate() {
		return date;
	}
	
	/**
	 * Affecte la date à laquelle la plage se rapporte.
	 * @param date la date à laquelle la plage se rapporte.
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

// Getter et setter pour l'attribut "heureDebut"
	
	/**
	 * Renvoie l'heure de début de la plage.
	 * @return l'heure de début de la plage
	 */
	public LocalTime getHeureDebut() {
		return heureDebut;
	}
	
	/**
	 * Affecte l'heure de début de la plage.
	 * @param heureDebut l'heure de début de la plage
	 */
	public void setHeureDebut(LocalTime heureDebut) {
		this.heureDebut = heureDebut;
	}
	
// Getter et setter pour l'attribut "heureFin"
	
	/**
	 * Renvoie l'heure de fin de la plage.
	 * @return l'heure de fin de la plage
	 */
	public LocalTime getHeureFin() {
		return heureFin;
	}
	
	/**
	 * Affecte l'heure de fin de la plage.
	 * @param heureFin l'heure de fin de la plage
	 */
	public void setHeureFin(LocalTime heureFin) {
		this.heureFin = heureFin;
	}

// Getter et setter pour l'attribut "eve"
	
	/**
	 * Renvoie la référence de l'objet <code>Evenement</code> auquel l'objet courant se rapporte.
	 * @return la référence de l'objet <code>Evenement</code> auquel l'objet courant se rapporte
	 * @see Evenement
	 */
	public Evenement getEve()	{
		return eve;
	}
	
	/**
	 * Affecte la référence de l'objet <code>Evenement</code> auquel l'objet courant se rapporte.
	 * @param eve  la référence de l'objet <code>Evenement</code> auquel l'objet courant se rapporte.
	 * @see Evenement
	 */
	public void setEve(Evenement eve) {
		this.eve = eve;
	}
	
// Getter et setter pour l'attribut "listeInscription"
	
	/**
	 * Renvoie la référence de la liste des objets <code>Inscription</code> liés à l'objet courant.
	 * @return la référence de la liste des objets <code>Inscription</code> liés à l'objet courant
	 * @see Inscription
	 * @see java.util.LinkedList
	 */
	public LinkedList<Inscription> getListeInscription()
	{
		return listeInscription;
	}
	
	/**
	 * Affecte la référence d'une liste d'objets <code>Inscription</code> à l'objet courant..
	 * @param listeInscription la référence d'une liste d'objets <code>Inscription</code> à affecter à l'objet courant.
	 * @see Inscription
	 *  @see java.util.LinkedList
	 */
	public void setListeInscription(LinkedList<Inscription> listeInscription)
	{
		this.listeInscription = listeInscription;
	}

	/*
	 * Methodes redondantes
	 * 
	 * public LinkedList<Inscription> getInscription(){
	 *	return listeInscription;
	 * }
	 * public void setInscription(LinkedList<Inscription> i) {
	 *	listeInscription = i;
	 * }
	 */
	
//###################################################################################################################################################################
	
	// Manipulation des listes
	
//###################################################################################################################################################################
	
	/**
	 * Ajoute un objet <code>Inscription</code> à l'objet courant.  <br><br>
	 * Précondition: l'attribut "<code>listeInscription</code>" est initialisé avec un type valide (<code>Inscription</code>); l'objet <code>Inscription i</code>  est correctement initialisé. <br>
	 * Postcondition: l'objet <code>Inscription i</code> a été ajouté à l'objet courant. <br>
	 * @param i l'<code>Inscription</code> à ajouter à la liste "<code>listeInscription</code>"
	 * @see Inscription
	 * @see java.util.LinkedList#add(Object)
	 */
	public void addInscription(Inscription i) {
		listeInscription.add(i);
	}
	
	/**
	 * Supprime un objet <code>Inscription</code> de l'objet courant. <br><br>
	 * Précondition: l'attribut "<code>listeInscription</code>" est initialisé. <br>
	 * Postcondition: si l'objet <code>Inscription i</code> se trouvait dans la liste, il en a été supprimé; sinon l'objet courant est inchangé. <br>
	 * @param i l'objet <code>Inscription</code> à supprimer de l'objet courant
	 * @see Inscription
	 * @see java.util.LinkedList#remove(Object)
	 */
	public void delInscription(Inscription i) {
		listeInscription.remove(i);
	}
	
}
