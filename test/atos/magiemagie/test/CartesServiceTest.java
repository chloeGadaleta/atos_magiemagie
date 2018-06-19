/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

import atos.magieMagie.Entity.Carte;
import atos.magieMagie.service.CarteService;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrateur
 */
public class CartesServiceTest {
    
   private CarteService carteService = new CarteService();
   
   @Test
   public void listerCarteJoueurOK(){
       
       List<Carte> c = carteService.listerCartesJoueur(15);
       assertEquals(7, c.size());
   }
}
