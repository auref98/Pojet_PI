package Class_PI;

class Inscription {
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
  
}
