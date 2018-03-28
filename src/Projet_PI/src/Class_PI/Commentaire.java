package Class_PI;

class Commentaire {
  private String contenu;

  private Representant poste;
  
  //constructeur commentaire vide
  //pré:
  //post:contenu initialisé a null
  Commentaire(){
	  this.contenu=null;
  }
  //constructeur commentaire
  //pré:contenu initialisé, contient au moins un caractère
  //post:contenu initialisé avec le nouveau contenu
  Commentaire(String contenu){
	  this.contenu=contenu;
  }
}
