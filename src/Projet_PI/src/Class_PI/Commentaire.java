package Class_PI;

class Commentaire {
  private String contenu;

  private Representant poste;
  
  //constructeur commentaire vide
  //pr�:
  //post:contenu initialis� a null
  Commentaire(){
	  this.contenu=null;
  }
  //constructeur commentaire
  //pr�:contenu initialis�, contient au moins un caract�re
  //post:contenu initialis� avec le nouveau contenu
  Commentaire(String contenu){
	  this.contenu=contenu;
  }
}
