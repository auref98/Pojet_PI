package Class_PI;

import java.util.*;

class Contact {
  private String mail;

  private Evenement eve;
  private ArrayList<Section> interesse;
  
  //constructeur contact
  //pré:la variable mail est initialisé
  //post:mail contient maintenant le nouveau mail
  Contact(String mail){
	  this.mail=mail;
  }
}
