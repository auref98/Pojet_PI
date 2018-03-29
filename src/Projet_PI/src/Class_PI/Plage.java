package Class_PI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public class Plage {
  private LocalDate date;
  private LocalTime heureDebut;
  private LocalTime heureFin;

  private Evenement eve;
  private LinkedList<Inscription> listeInscri;

  /**
   * 
   * @param date
   * @param heureDebut
   * @param heureFin
   * @param eve
   */
  Plage(LocalDate date,LocalTime heureDebut,LocalTime heureFin, Evenement eve){
	  this.date=date;
	  this.heureDebut=heureDebut;
	  this.heureFin=heureFin;
	  this.eve = eve;
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
