/*
 * NamingException(Aur�lien, Killian, Robin, Louis, Christophe)
 */

package Class_PI;

import java.util.*;

public class Contact {
  private String mail;

  private Evenement eve;
  private ArrayList<Section> interesse;
  
  //constructeur contact
  //pr�:la variable mail est initialis�
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
