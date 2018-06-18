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
public class PartieServiceTest {

    private PartieService partieService = new PartieService();
    private JoueurService joueurService = new JoueurService();

    @Test
    public void creerNouvellePartieOK() {

        Partie p = partieService.creerNouvellePartie("partie 1");
        assertNotNull(p.getId());

    }
    
    @Test
    public void demarrerPartieOK() {

        Partie p = partieService.creerNouvellePartie("test1");
        
        joueurService.rejoindrePartie("Chloé", "gadaleta", p.getId());
        joueurService.rejoindrePartie("Anouck", "gadaleta", p.getId());
        joueurService.rejoindrePartie("Chantal", "gadaleta", p.getId());
        
        partieService.demarrerPartie(p.getId());
        
       
    }

}
