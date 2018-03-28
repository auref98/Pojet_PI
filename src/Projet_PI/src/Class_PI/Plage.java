package Class_PI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

class Plage {
  private LocalDate date;
  private LocalTime heureDebut;
  private LocalTime heureFin;

  private Evenement eve;
  private LinkedList<Inscription> listeInscri;

  //constructeur de plage
  //pré:variables correctement initialisées
  //post:variables correctement ajoutées
  Plage(LocalDate date,LocalTime heureDebut,LocalTime heureFin){
	  this.date=date;
	  this.heureDebut=heureDebut;
	  this.heureFin=heureFin;
  }
}
