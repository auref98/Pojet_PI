package Class_PI;

import java.util.ArrayList;

public class Adresse {
  private int id;
  private String localite;
  private int codePostal;
  private String rue;
  private int numero;
  private String boit;
  private String pays;

  private Evenement eve;
  private ArrayList<Etudiant> reside;

  /*
   * constructeur Adresse
   * precondition : toute les variables sont initialis�
   * postcondition : le constructeur est initialiser
   */
  Adresse(String localite, int codePostal,String rue,int numero,String boite,String pays){
	  this.localite = localite;
	  this.codePostal = codePostal;
	  this.rue = rue;
	  this.numero = numero;
	  this.boit = boite;
	  this.pays = pays;
  }
  public Adresse clone(){
	  return new Adresse(this.localite,this.codePostal,this.rue,this.numero,this.boit,this.pays);
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
	 * @return the boit
	 */
	public String getBoit() {
		return boit;
	}
	
	/**
	 * @param boit the boit to set
	 */
	public void setBoit(String boit) {
		this.boit = boit;
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
}
