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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

/**
 * Classe de type "bean" repr�sentant une plage horaire. <br><br>
 * Elle renseigne la date associ�e � cette plage horaire et ses heures de d�but et de fin, ainsi que l'objet <code>Evenement</code> auquel elle se rapporte. <br>
 * elle ne repr�sente pas l'�v�nement lui-m�me mais une partie de celui-ci. Un <code>Evenement</code> est mod�lis� par un ensemble de <code>Plage</code> distinctes. <br>
 * Cette classe a �t� con�ue afin de mod�liser une plage horaire sur une seule journ�e; elle ne fonctionne pas pour repr�senter une dur�e s'�tendant au del� de minuit. <br>
 * @see Evenement
 * @see Inscription
 * @see java.util.LinkedList
 * @see java.time.LocalDate
 * @see java.time.LocalTime
 */
public class Plage implements Serializable{
	
	private int id;													// Attribut permettant de r�cuperer l'id r�f�ren�ant cette adresse dans la base de donn�es
	private LocalDate date;
	private LocalTime heureDebut;
	private LocalTime heureFin;
	
	private Evenement eve;											// R�f�rence vers l'objet Evenement auquel se rapporte la plage horaire se rapporte
	private LinkedList<Inscription> listeInscription;						// R�f�rence vers la liste des objets Inscription relatifs � cette plage horaire (Une Inscription lie une Plage � un objet Representant)
	
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/** Constructeur sans param�tre. */
	public Plage() {}
	
	/**
	 * Constructeur initialisant tous les attributs de l'objet. <br><br>
	 * Pr�condition:  tous les param�tres sont correctement initialis�s. <br>
	 * Postcondition: l'objet a �t� initialis�, tous ses champs (� l'exception de "<code>listeInscription</code>" sont initialis�s avec la valeur du param�tre de m�me nom. <br>
	 * @param id l'identifiant (BD) de la plage
	 * @param date la date � laquelle la plage se rapporte. au format <code>java.time.LocalDate</code>
	 * @param heureDebut l'heure de d�but de la plage au format <code>java.time.LocalTime</code>
	 * @param heureFin l'heure de fin de la plage au format <code>java.time.LocalTime</code>
	 * @param eve la r�f�rence de l'Evenement auquel la plage se rapporte
	 */
	public Plage(int id, LocalDate date, LocalTime heureDebut, LocalTime heureFin, Evenement eve){
		this.id = id;
		this.date=date;
		this.heureDebut=heureDebut;
		this.heureFin=heureFin;
		this.eve = eve;
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
	 * Renvoie la date � laquelle la plage se rapporte.
	 * @return la date � laquelle la plage se rapporte.
	 */
	public LocalDate getDate() {
		return date;
	}
	
	/**
	 * Affecte la date � laquelle la plage se rapporte.
	 * @param date la date � laquelle la plage se rapporte.
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

// Getter et setter pour l'attribut "heureDebut"
	
	/**
	 * Renvoie l'heure de d�but de la plage.
	 * @return l'heure de d�but de la plage
	 */
	public LocalTime getHeureDebut() {
		return heureDebut;
	}
	
	/**
	 * Affecte l'heure de d�but de la plage.
	 * @param heureDebut l'heure de d�but de la plage
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
	 * Renvoie la r�f�rence de l'objet <code>Evenement</code> auquel l'objet courant se rapporte.
	 * @return la r�f�rence de l'objet <code>Evenement</code> auquel l'objet courant se rapporte
	 * @see Evenement
	 */
	public Evenement getEve()	{
		return eve;
	}
	
	/**
	 * Affecte la r�f�rence de l'objet <code>Evenement</code> auquel l'objet courant se rapporte.
	 * @param eve  la r�f�rence de l'objet <code>Evenement</code> auquel l'objet courant se rapporte.
	 * @see Evenement
	 */
	public void setEve(Evenement eve) {
		this.eve = eve;
	}
	
// Getter et setter pour l'attribut "listeInscription"
	
	/**
	 * Renvoie la r�f�rence de la liste des objets <code>Inscription</code> li�s � l'objet courant.
	 * @return la r�f�rence de la liste des objets <code>Inscription</code> li�s � l'objet courant
	 * @see Inscription
	 * @see java.util.LinkedList
	 */
	public LinkedList<Inscription> getListeInscription()
	{
		return listeInscription;
	}
	
	/**
	 * Affecte la r�f�rence d'une liste d'objets <code>Inscription</code> � l'objet courant..
	 * @param listeInscription la r�f�rence d'une liste d'objets <code>Inscription</code> � affecter � l'objet courant.
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
	 * Ajoute un objet <code>Inscription</code> � l'objet courant.  <br><br>
	 * Pr�condition: l'attribut "<code>listeInscription</code>" est initialis� avec un type valide (<code>Inscription</code>); l'objet <code>Inscription i</code>  est correctement initialis�. <br>
	 * Postcondition: l'objet <code>Inscription i</code> a �t� ajout� � l'objet courant. <br>
	 * @param i l'<code>Inscription</code> � ajouter � la liste "<code>listeInscription</code>"
	 * @see Inscription
	 * @see java.util.LinkedList#add(Object)
	 */
	public void addInscription(Inscription i) {
		listeInscription.add(i);
	}
	
	/**
	 * Supprime un objet <code>Inscription</code> de l'objet courant. <br><br>
	 * Pr�condition: l'attribut "<code>listeInscription</code>" est initialis�. <br>
	 * Postcondition: si l'objet <code>Inscription i</code> se trouvait dans la liste, il en a �t� supprim�; sinon l'objet courant est inchang�. <br>
	 * @param i l'objet <code>Inscription</code> � supprimer de l'objet courant
	 * @see Inscription
	 * @see java.util.LinkedList#remove(Object)
	 */
	public void delInscription(Inscription i) {
		listeInscription.remove(i);
	}
	
}
