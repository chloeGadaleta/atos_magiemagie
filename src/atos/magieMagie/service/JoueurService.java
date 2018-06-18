/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.service;

import atos.magieMagie.Dao.JoueurDAO;
import atos.magieMagie.Dao.PartieDAO;
import atos.magieMagie.Entity.Joueur;
import atos.magieMagie.Entity.Partie;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public class JoueurService {
    
    
    private JoueurDAO dao = new JoueurDAO();
    private PartieDAO partiedao = new PartieDAO();
    
    
    public Joueur rejoindrePartie(String pseudo, String avatar, long idPartie){
        
        
        // recherche si le joueur existe déjà
        Joueur joueur = dao.rechercherParPseudo(pseudo);
        if (joueur == null) {
            // joueur n'existe pas encore donc je l'instancie
            
            joueur = new Joueur();
            joueur.setPseudo(pseudo);
            joueur.setNbrPartiesGagnees(0L);
            joueur.setNbrPartiesJouees(0L);
        }
        
        // je prends le joueur et je set son avatar ( je sors du if)
          joueur.setAvatar(avatar);
        
        // la partie n'a pas encore démarrée donc aucun joueur n'a la main
        joueur.setEtatJoueur(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
        long ordre = dao.rechercheOrdreNouveauJoueurPourPartieIs(idPartie);
        joueur.setOrdre(ordre);
//        
//      // reccupérer la partie
        Partie partie = partiedao.rechercherParId(idPartie);

//        
//      // maintenant qu'on a reccup la partie il faut associer le joueur à la partie
         joueur.setPartie(partie);
        partie.getJoueurs();
        List<Joueur> listeJoueurs = partie.getJoueurs();
        listeJoueurs.add(joueur);
        
        if(joueur.getId()==null){ //nouveau
            dao.ajouter(joueur);
        }else{
            dao.modifier(joueur);
        }
        
//        
//        
        return joueur;
    }
    
      private void afficherCartesJoueur(Joueur joueur){
        
    }
    
    
   
}
