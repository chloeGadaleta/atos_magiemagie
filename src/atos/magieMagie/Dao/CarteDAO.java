/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.Dao;

import atos.magieMagie.Entity.Carte;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
public class CarteDAO {
    
    public List<Carte> listerCartesJoueur(long idJoueur){
        
        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
        
        Query query = em.createQuery("SELECT c"
                + "                   FROM Carte c"
                + "                        JOIN c.joueur j"
                + "                   WHERE j.id =:id_joueur "
                );
        
        query.setParameter("id_joueur", idJoueur);
        return query.getResultList();
    }
    
    
    public void majCarte(Carte carte){
        
        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
        em.getTransaction().begin();
        em.merge(carte);
        em.getTransaction().commit();
    
    }
}
