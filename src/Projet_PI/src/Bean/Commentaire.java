/*
 * NamingException(Aurélien, Killian, Robin, Louis, Christophe)
 */

package Bean;

public class Commentaire {
	private int id;
	private String contenu;
	
	private Representant rep;
	private Evenement evenement;
	
	public Commentaire(int id, String contenu) {
		super();
		this.id = id;
		this.contenu = contenu;
	}
	
	public Commentaire() {

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
