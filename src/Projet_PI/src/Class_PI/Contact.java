package Class_PI;

import java.util.*;

class Contact {
  private String mail;

  private Evenement eve;
  private ArrayList<Section> interesse;
  
  //constructeur contact
  //pr�:la variable mail est initialis�
  //post:mail contient maintenant le nouveau mail
  Contact(String mail){
	  this.mail=mail;
  }
}
