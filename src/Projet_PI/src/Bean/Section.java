/*
 * NamingException(Aurélien, Killian, Robin, Louis, Christophe)
 */

package Bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.io.Serializable;

public class Section implements Serializable {
  private int id;
  private String nom;
  

  private LinkedList<Etudiant> listeEtudiant;
  private LinkedList<Evenement> listeEvent;
  private LinkedList<Contact> listeContact;
  private Professeur relais;
  private ArrayList<Professeur> listeProf;
  
  	Section(){}
  	
	public Section(String nom, Professeur relais)
	{
		super();
		this.nom = nom;
		this.relais = relais;
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
	 * @return the relais
	 */
	public Professeur getRelais() {
		return relais;
	}

	/**
	 * @param relais the relais to set
	 */
	public void setRelais(Professeur relais) {
		this.relais = relais;
	}
	
	
}
