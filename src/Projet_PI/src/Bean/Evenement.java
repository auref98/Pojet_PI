/*
 * Haute école Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integre: realisation d'un logiciel de gestion des inscriptions à des evenements
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

public class Evenement implements Serializable{
	
	private int id;
	private String nom;
	private int nbParticipantRequis;
	private String description;
	private String image;
	private Adresse adresseEve;

	private ArrayList<Plage> listePlage;
	private LinkedList<Commentaire> listeCommentaire;
	private LinkedList<Contact> listeContact;
	private ArrayList<Section> listeSection;
  
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/** Constructeur sans paramètre. */
	public Evenement() {}
	
	public Evenement(int id, String nom, int nbParticipantRequis, String description, String image, Adresse adresseEve)
	{
		this.id = id;
		this.nom = nom;
		this.nbParticipantRequis = nbParticipantRequis;
		this.description = description;
		this.image = image;
		this.adresseEve = adresseEve;
		
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
	 * 
	 * @author Aurelien
	 * @param nom est initialiser
	 * @return true si le nom est égale au nom de l'évènement
	 */
	public boolean equals(String nom){
		return nom.equals(this.nom);
	}

//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the nbParticipantRequis
	 */
	public int getNbParticipantRequis() {
		return nbParticipantRequis;
	}

	/**
	 * @param nbParticipantRequis the nbParticipantRequis to set
	 */
	public void setNbParticipantRequis(int nbParticipantRequis) {
		this.nbParticipantRequis = nbParticipantRequis;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the srcImage
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param srcImage the srcImage to set
	 */
	public void setImage(String srcImage) {
		this.image = image;
	}

	/**
	 * @return the adresseEve
	 */
	public Adresse getAdresseEve() {
		return adresseEve;
	}

	/**
	 * @param adresseEve the adresseEve to set
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
