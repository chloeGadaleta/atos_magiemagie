/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

import atos.magieMagie.Entity.Partie;
import atos.magieMagie.service.JoueurService;
import atos.magieMagie.service.PartieService;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrateur
 */
public class JoueurServiceTest {
    
  private JoueurService service = new JoueurService();
  private PartieService partieService = new PartieService();
    @Test 
    public void rejoindreServiceOK(){
       
        // il renvoie une entité partie
        Partie nouvellePartie = partieService.creerNouvellePartie("partie1");
        
        service.rejoindrePartie("thomas", "blabla", nouvellePartie.getId());
        
        
  }
    
}
