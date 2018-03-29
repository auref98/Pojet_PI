/*
 * NamingException(Aurélien, Killian, Robin, Louis, Christophe)
 */

package Bean;

import java.util.*;

public class Contact {
  private int id;
  private String mail;

  private Evenement eve;
  private ArrayList<Section> interesse;
  
  //constructeur contact
  //pré:la variable mail est initialisé
  //post:mail contient maintenant le nouveau mail
  Contact(String mail){
	  this.mail=mail;
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
}
