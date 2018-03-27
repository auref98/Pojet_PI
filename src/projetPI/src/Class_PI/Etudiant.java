package Class_PI;

<<<<<<< HEAD:src/Etudiant.java
import java.time.LocalDate;
=======
import java.time.*;
import java.util.*;
>>>>>>> 263a235f873da958a455f3e40307c6f0b7a25446:src/projetPI/src/Class_PI/Etudiant.java

class Etudiant extends Representant{
  private LocalDate dateNaissance;
  private String paysDeNaissance;
  private String lieuDeNaissance;
  private int numeroNational;
  private String nationalite;
  private int iban;
  private boolean conseilSocial;
  private String lieuEcole;
  private String role;//ouvrier ou presenter au stand
  private int disponibilite;/*0 -> matin
                              1 -> apres-midi
                              2 -> matin et apres-midi*/

  private Adresse reside;
  private Section etudie;

  Etudiant(){

  }
}
