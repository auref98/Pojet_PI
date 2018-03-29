/*
 * NamingException(Aurélien, Killian, Robin, Louis, Christophe)
 */

package Bean;

public class Inscription {
  private boolean valide;
  private Representant representant;
  private Plage plage;

  //constructeur 
  //pré:le representant est la personne qui lance l'inscription, la plage est celle a laquelle il veut participer
  //post: les attibuts sont initialisés
  Inscription(Representant r, Plage p){
	  	valide = false; //en attendant que le charge de comm ne valide la présence l'attribut est a false
	  	representant = r;
	  	plage = p;
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
