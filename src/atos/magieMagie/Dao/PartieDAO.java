/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.Dao;

import atos.magieMagie.Entity.Joueur;
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
    
   public Long compterJoueurPartie(long idPartie){
       
       EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();
       
       Query query = em.createQuery("SELECT COUNT(j)"
               + "                        FROM Joueur j"
               + "                        WHERE j.partie.id = :id_partie"
               );
       
       query.setParameter("id_partie", idPartie);
       return (Long) query.getSingleResult();
       
   }
    
    public List<Partie> listerPartiesNonDemarrees() {

        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();

        Query query = em.createQuery("SELECT p "
                + "FROM Partie p "
                + "EXCEPT"
                + "SELECT p2.id"
                + "FROM Partie p2"
                + "     JOIN p.joueurs j"
                + "WHERE j.etat=:etat_gagne"
                + "EXCEPT"
                + "SELECT p"
                + "FROM Partie p"
                + "     JOIN p.joueurs j"
                + "WHERE j.etat=:etat_alamain"
        );

        query.setParameter("etat_gagne", Joueur.EtatJoueur.GAGNE);
        query.setParameter("etat_alamain", Joueur.EtatJoueur.A_LA_MAIN);

        return query.getResultList();
    }

    public void ajouterPartie(Partie p) {

        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();

        em.getTransaction().begin();

        em.persist(p);

        em.getTransaction().commit();
    }

    public Partie rechercherParId(long idPartie) {

        EntityManager em = Persistence.createEntityManagerFactory("MagieMagiePU").createEntityManager();

        // on peut faire seulement un find si l'on a juste besoin de la clé primaire
        return em.find(Partie.class, idPartie);
    }

}
