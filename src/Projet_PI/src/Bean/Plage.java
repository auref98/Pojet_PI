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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public class Plage implements Serializable{
	
	private int id;
	private LocalDate date;
	private LocalTime heureDebut;
	private LocalTime heureFin;
	
	private Evenement eve;
	private LinkedList<Inscription> listeInscription;
	
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/** Constructeur sans paramètre. */
	public Plage() {}
	
	/**
	 * 
	 * @param date
	 * @param heureDebut
	 * @param heureFin
	 * @param eve
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
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}
	
	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	/**
	 * @return the heureDebut
	 */
	public LocalTime getHeureDebut() {
		return heureDebut;
	}
	
	/**
	 * @param heureDebut the heureDebut to set
	 */
	public void setHeureDebut(LocalTime heureDebut) {
		this.heureDebut = heureDebut;
	}
	
	/**
	 * @return the heureFin
	 */
	public LocalTime getHeureFin() {
		return heureFin;
	}
	
	/**
	 * @param heureFin the heureFin to set
	 */
	public void setHeureFin(LocalTime heureFin) {
		this.heureFin = heureFin;
	}
	
	public LinkedList<Inscription> getListeInscription()
	{
		return listeInscription;
	}
	public void setListeInscription(LinkedList<Inscription> listeInscription)
	{
		this.listeInscription = listeInscription;
	}
	public Evenement getEve()
	{
		return eve;
	}
	
//###################################################################################################################################################################
	
	// Manipulation des listes
	
//###################################################################################################################################################################
	
	public void addInscription(Inscription i) {
		listeInscription.add(i);
	}
	public void delInscription(Inscription i) {
		listeInscription.remove(i);
	}
	public LinkedList<Inscription> getInscription(){
		return listeInscription;
	}
	public void setInscription(LinkedList<Inscription> i) {
		listeInscription = i;
	}
	public void setEve(Evenement eve){
		this.eve = eve;
	}
	
}
