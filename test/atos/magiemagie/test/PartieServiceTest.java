/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

import atos.magieMagie.Entity.Partie;
import atos.magieMagie.service.PartieService;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrateur
 */
public class PartieServiceTest {
    
    private PartieService service = new PartieService();
    
    @Test
    public void creerNouvellePartieOK(){
        
        Partie p = service.creerNouvellePartie("partie 1");
        assertNotNull(p.getId());
        
        
    }
    
}
