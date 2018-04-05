/*
 * NamingException(Aurélien, Killian, Robin, Louis, Christophe)
 */

package Bean;

import java.util.*;
import java.io.Serializable;

public class Contact implements Serializable {
  private int id;
  private String mail;

  private Evenement eve;
  private ArrayList<Section> interesse;
  
  //constructeur contact
  //pré:la variable mail est initialisé
  //post:mail contient maintenant le nouveau mail  
  public Contact(){}
  
  public Contact(String mail, Evenement eve){
	  this.mail=mail;
	  this.eve = eve;
	  interesse = new ArrayList<Section>();
  }
	
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
	 * @return the mail
	 */
	public Evenement getEve() {
		return eve;
	}
	
	/**
	 * @param mail the mail to set
	 */
	public void setEve(Evenement eve) {
		this.eve = eve;
	}
	
	public void addInteresse(Section s) {
		interesse.add(s);
	}
	
	public void delInteresse(Section s) {
		interesse.remove(s);
	}
}
