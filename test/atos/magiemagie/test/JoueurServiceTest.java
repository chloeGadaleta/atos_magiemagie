/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

import atos.magieMagie.Entity.Joueur;
import atos.magieMagie.Entity.Partie;
import atos.magieMagie.service.JoueurService;
import atos.magieMagie.service.PartieService;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrateur
 */
public class JoueurServiceTest {
    
  private JoueurService joueurService = new JoueurService();
  private PartieService partieService = new PartieService();
   
  @Test
  public void listerJoueurEtNbrCarteOK(){
      
  }
  
  @Test 
  public void ordreJoueurOK(){
      
     Partie nouvellePartie = partieService.creerNouvellePartie("ordreJoueurOk");
     
     //Indiquer l'ordre des joueurs
     joueurService.rejoindrePartie("A", "A", nouvellePartie.getId());
     joueurService.rejoindrePartie("B", "B", nouvellePartie.getId());
     Joueur j = joueurService.rejoindrePartie("C", "C", nouvellePartie.getId());
     
     assertEquals(2L, (long) j.getOrdre());
      
  }
  
  @Test 
    public void rejoindreServiceOK(){
       
        // il renvoie une entité partie
        Partie nouvellePartie = partieService.creerNouvellePartie("partie1");
        
        joueurService.rejoindrePartie("thomas", "blabla", nouvellePartie.getId());
        
  }
    
  
    
}
