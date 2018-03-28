package Class_PI;

import java.util.ArrayList;
import java.util.LinkedList;

class Section {
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
}
