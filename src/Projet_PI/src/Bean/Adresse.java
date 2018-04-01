/*
 * NamingException(Aurélien, Killian, Robin, Louis, Christophe)
 */

package Bean;

import java.util.ArrayList;
import java.io.Serializable;

public class Adresse implements Serializable {
  private int id;
  private String localite;
  private int codePostal;
  private String rue;
  private int numero;
  private String boite;
  private String pays;

  private ArrayList<Evenement> listeEvent;
  private ArrayList<Etudiant> listeEtudiant;

  /*
   * constructeur Adresse
   * precondition : toutes les variables sont initialisées
   * postcondition : le constructeur est initialisé
   */
  public Adresse() {}
  
  public Adresse(int id, String localite, int codePostal,String rue,int numero,String boite,String pays){
	  this.id = id;
	  this.localite = localite;
	  this.codePostal = codePostal;
	  this.rue = rue;
	  this.numero = numero;
	  this.boite = boite;
	  this.pays = pays;
	  listeEvent = new ArrayList<Evenement> (); 
	  listeEtudiant = new ArrayList<Etudiant> ();
	  
  }	
	/**
	 * @return the localite
	 */
	public String getLocalite() {
		return localite;
	}
	
	/**
	 * @param localite the localite to set
	 */
	public void setLocalite(String localite) {
		this.localite = localite;
	}
	
	/**
	 * @return the codePostal
	 */
	public int getCodePostal() {
		return codePostal;
	}
	
	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	
	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}
	
	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}
	
	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}
	
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	/**
	 * @return the boite
	 */
	public String getBoite() {
		return boite;
	}
	
	/**
	 * @param boit the boite to set
	 */
	public void setBoite(String boite) {
		this.boite = boite;
	}
	
	/**
	 * @return the pays
	 */
	public String getPays() {
		return pays;
	}
	
	/**
	 * @param pays the pays to set
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}
	
	public void addEtudiant(Etudiant e) {
		listeEtudiant.add(e);
	}
	
	public void addEvent(Evenement e) {
		listeEvent.add(e);
	}
	
	public void delEtudiant(Etudiant e) {
		listeEtudiant.remove(e);
	}
	
	public void delEvent(Evenement e) {
		listeEvent.remove(e);
	}
	
	public void getEtudiant() {
		int taille = listeEtudiant.size();
		int i = 0;
		while (i<taille) {
			Etudiant e = listeEtudiant.get(i);
			System.out.println(e.toString());
			i++;
		}
	}
	
	public void getEvent() {
		int taille = listeEvent.size();
		int i = 0;
		while (i<taille) {
			Evenement e = listeEvent.get(i);
			System.out.println(e.toString());
			i++;
		}
	}
	
	public String toString(){
		return("Rue "+rue+", "+ numero + "/n"+ codePostal + localite + "( " + pays + " )");
	}
}
