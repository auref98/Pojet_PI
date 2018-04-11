/*
 * NamingException(Aurélien, Killian, Robin, Louis, Christophe)
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
	//##################################################################
	//getter setter
	//##################################################################
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
	//#####################################################################
	//manip liste
	//#####################################################################
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
