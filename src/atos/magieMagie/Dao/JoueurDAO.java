/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.Dao;

import atos.magieMagie.Entity.Joueur;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
public class JoueurDAO {
    
    /**
     * Renvoie le Joueur dont le pseudo existe en bdd, ou renvoie null si pas trouvé
     * @param pseudo
     * @return 
     */
    
    // recherche si joueur existe par pseudo
    public Joueur rechercherParPseudo(String pseudo){
        
        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
        
        // recherche dans bdd le joueur par 
//        Query query = em.createQuery("SELECT j"
//                + "FROM Joueur j"
//                + "WHERE j.pseudo =:lepseudo"
//                );
              Query query = em.createQuery("select j from Joueur j where j.pseudo =:lePseudo");
              query.setParameter("lePseudo", pseudo);
        
        // si ma liste est vide alors il renvoie le return
        List<Joueur> joueursTrouves = query.getResultList();
        
        if (joueursTrouves.isEmpty())
            return null;
        // correspond au else car le return qui la fonction, dc si j'arrive ici c'est que la fonction était fausse
        return  (Joueur) query.getSingleResult();
    
    }
    
    public long rechercheOrdreNouveauJoueurPourPartieIs(long partieId){
        
        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
        
//        Query query = em.createQuery("SELECT MAX(j.ordre)+1"
//                + "FROM Joueur j"
//                + "     JOIN j.partie p"
//                + "WHERE j.id =:idPartie"
//                );
             Query query = em.createQuery("select Max(j.ordre)+1 from Joueur j join j.partie p where j.id =:idPartie ");
        
        query.setParameter("idPartie", partieId);
        Object res = query.getSingleResult();
        if (res == null) {
            return 1;
        }
        return (long) res;
    } 


    public void ajouter(Joueur joueur) {
        
           EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
           em.getTransaction().begin();
           
           em.persist(joueur);
           
           em.getTransaction().commit();
                
    }
    
    public void modifier(Joueur joueur) {
        
//        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
        
        
    }
}
