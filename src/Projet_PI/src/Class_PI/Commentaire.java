package Class_PI;

public class Commentaire {
  private String contenu;

  private Representant poste;
  
  //constructeur commentaire
  //pr�:contenu initialis�, contient au moins un caract�re
  //post:contenu initialis� avec le nouveau contenu
  public Commentaire(String contenu){
	  this.contenu=contenu;
  }
	
	/**
	 * @return the contenu
	 */
	public String getContenu() {
		return contenu;
	}
	
	/**
	 * @param contenu the contenu to set
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
}
