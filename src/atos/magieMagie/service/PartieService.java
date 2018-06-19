/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.service;

import atos.magieMagie.Dao.CarteDAO;
import atos.magieMagie.Dao.JoueurDAO;
import atos.magieMagie.Dao.PartieDAO;
import atos.magieMagie.Entity.Carte;
import atos.magieMagie.Entity.Carte.TypeIngredient;
import atos.magieMagie.Entity.Joueur;
import atos.magieMagie.Entity.Partie;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Administrateur
 */
public class PartieService {
   
    
   private  PartieDAO partiedao = new PartieDAO();
   private  JoueurDAO joueurdao = new JoueurDAO();
   private CarteDAO carteDao=new CarteDAO();
   
   
    /**
     * Liste des parties dont aucun joueur n'est à l'état A_LA_MAIN
     * ou GAGNE
     * @return 
     */
   public List<Partie> listerPartiesNonDemarrees(){
       
       return partiedao.listerPartiesNonDemarrees();
   }
   
   public Partie creerNouvellePartie(String nom){
       
       // on crée la classe
       Partie p = new Partie();
       p.setNom(nom);
       partiedao.ajouterPartie(p);
       
       return p;
       
       
    }
   
   public void demarrerPartie(long idPartie){
       
       // recherche par id  
       Partie p = partiedao.rechercherParId(idPartie);
       
       // Erreur si on a pas au moins deux joueurs dans la partie       
       if( partiedao.compterJoueurPartie(idPartie)<2  ){
            throw new RuntimeException("il n'y a pas assez de joueur");
       }
       
      // On passe le joueur d'ordre 0 à l'état A_LA_MAIN
      // Faire boucle et ajouter en base
       for (Joueur joueur : p.getJoueurs()) {         
           if (joueur.getOrdre()==0) {  
               joueur.setEtatJoueur(Joueur.EtatJoueur.A_LA_MAIN);
               joueurdao.modifier(joueur);
           }
       }
       
       // Distribue 7 cartes au hasard à chaque joueur de la partie
       for (Joueur joueur : p.getJoueurs() ) {
           for (int i = 0; i < 7; i++) {
               Carte carte = nouvelleCarte();
               joueur.getCartes().add(nouvelleCarte());
               carte.setJoueur(joueur);
               carteDao.majCarte(carte);
           }
       }
   }
    
        // tirage au hasard des cartes de 1 à 5 ingrédients
         private Carte nouvelleCarte() {

            TypeIngredient[] tabTypeIngredients = TypeIngredient.values();

            Random r = new Random();
            int n  = r.nextInt(tabTypeIngredients.length);

            Carte carte = new Carte();
            carte.setTypeIngredient(tabTypeIngredients[n]);
            return carte;
         }
}
