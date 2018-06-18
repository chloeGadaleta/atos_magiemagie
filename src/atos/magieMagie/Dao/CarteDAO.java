/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.Dao;

import atos.magieMagie.Entity.Carte;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Administrateur
 */
public class CarteDAO {
    
    public void majCarte(Carte carte){
        
        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
        em.getTransaction().begin();
        em.merge(carte);
        em.getTransaction().commit();
    
    }
}
