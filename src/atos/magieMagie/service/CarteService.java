/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.service;

import atos.magieMagie.Dao.CarteDAO;
import atos.magieMagie.Dao.JoueurDAO;
import atos.magieMagie.Entity.Carte;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public class CarteService {
    
    // recherche joueur par id
    private CarteDAO cartedao = new CarteDAO();
    private JoueurDAO joueurDao = new JoueurDAO();
    private CarteDAO carteDAO = new CarteDAO();
    
    public List<Carte> listerCartesJoueur(long idJoueur){
       
          return cartedao.listerCartesJoueur(idJoueur);   
    }

}

