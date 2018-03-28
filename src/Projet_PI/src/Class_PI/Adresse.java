package Class_PI;

import java.util.ArrayList;

class Adresse {
  private String localite;
  private int codePostal;
  private String rue;
  private int numero;
  private String boit;
  private String pays;

  private Evenement eve;
  private ArrayList<Etudiant> reside;

  Adresse(String localite, int codePostal,String rue,int numero,String boite,String pays){
	  this.localite = localite;
	  this.codePostal = codePostal;
	  this.rue = rue;
	  this.numero = numero;
	  this.boit = boite;
	  this.pays = pays;
  }
}
