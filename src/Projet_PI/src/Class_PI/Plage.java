package Class_PI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

class Plage {
  private LocalDate date;
  private LocalTime heureDebut;
  private LocalTime heureFin;

  private Evenement eve;
  private LinkedList<Inscription> listeInscri;

  //constructeur de plage
  //pré:variables correctement initialisées
  //post:variables correctement ajoutées
  Plage(LocalDate date,LocalTime heureDebut,LocalTime heureFin){
	  this.date=date;
	  this.heureDebut=heureDebut;
	  this.heureFin=heureFin;
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
	  
}
