/*
 * NamingException(Aurélien, Killian, Robin, Louis, Christophe)
 */

package Class_PI;

import java.util.ArrayList;
import java.util.LinkedList;

public class Section {
  private String nom;

  private LinkedList<Etudiant> listeEtudiant;
  private LinkedList<Evenement> listeEvent;
  private LinkedList<Contact> listeContact;
  private Professeur relais;
  private ArrayList<Professeur> listeProf;
  
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
}
