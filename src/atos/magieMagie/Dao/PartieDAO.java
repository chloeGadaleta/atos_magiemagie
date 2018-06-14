/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.Dao;

import atos.magieMagie.Entity.Partie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
public class PartieDAO {
    
       public List<Partie> listerPartiesNonDemarrees(){
           EntityManager em =  Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
           
           Query query = em.createQuery("SELECT p FROM Partie p WHERE p.id GROUP BY ");
           
           return query.getResultList();
       }
}
