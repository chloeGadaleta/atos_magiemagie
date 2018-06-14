/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.service;

import atos.magieMagie.Dao.PartieDAO;
import atos.magieMagie.Entity.Partie;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public class PartieService {
   
    
   private  PartieDAO dao = new PartieDAO();
    
   public List<Partie> listerPartiesNonDemarrees(){
       
       return dao.listerPartiesNonDemarrees();
   }
   
   public Partie creerNouvellePartie(long idJoueur , String nomPartie){
    
        return new Partie();
    }
   
   public Partie demarrerPartie(long idPartie, long idJoueur)(){
       
       return null;
   }
}
