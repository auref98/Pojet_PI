/*
 * NamingException(Aur�lien, Killian, Robin, Louis, Christophe)
 */

package Bean;

public class Inscription {
	
	private int id;
	private boolean valide;
	private Representant representant;
	private Plage plage;
	
	//constructeur 
	//pr�:le representant est la personne qui lance l'inscription, la plage est celle a laquelle il veut participer
	//post: les attibuts sont initialis�s
	Inscription(Representant r, Plage p){
	 	valide = false; //en attendant que le charge de comm ne valide la pr�sence l'attribut est a false
	  	representant = r;
	  	plage = p;
	}
	
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
	 * @return the valide
	 */
	public boolean isValide() {
		return valide;
	}
	
	/**
	 * @param valide the valide to set
	 */
	public void setValide(boolean valide) {
		this.valide = valide;
	}
	
	/**
	 * @return the representant
	 */
	public Representant getRepresentant() {
		return representant;
	}
	
	/**
	 * @param representant the representant to set
	 */
	public void setRepresentant(Representant representant) {
		this.representant = representant;
	}
	
	/**
	 * @return the plage
	 */
	public Plage getPlage() {
		return plage;
	}
	
	/**
	 * @param plage the plage to set
	 */
	public void setPlage(Plage plage) {
		this.plage = plage;
	}
	  
  
}
