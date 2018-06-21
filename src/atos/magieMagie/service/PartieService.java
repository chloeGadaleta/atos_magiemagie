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
   
   public void lancerUnSort(long idJoueur){
       
            
       
       
   }
   
   
    public void piocher(long idJoueur){
        
        // Le joueur pioche une carte
        
        Joueur joueur = joueurdao.rechercheJoueurParId(idJoueur);
        
        Carte carte = nouvelleCarte();
        
        // on lie la carte au joueur
        carte.setJoueur(joueur);
        // on ajoute cette carte à sa liste de carte
        joueur.getCartes().add(nouvelleCarte());
        
        carteDao.majCarte(carte);
        joueurdao.modifier(joueur);
        
    }
   
   
   
   public void passeJoueurSuivant(long idPartie){
        
        // Réccuper id du joueur que à la main
        Joueur joueurQuiALaMain = joueurdao.rechercherJoueurQuiALaMainParPartieId(idPartie);
        
        
        //Determine si tous les autres joueurs ont perdus
        //et passe le joueur à l'état gagné si c'est le cas puis quitte la fonction
        
        // pas besoin d'écrire true et else.. cela se fait automatiquement
        if(joueurdao.determineSiPlusQueUnJoueurDansPartie(idPartie)){
            
            joueurQuiALaMain.setEtatJoueur(Joueur.EtatJoueur.GAGNE);
            joueurdao.modifier(joueurQuiALaMain);
            return;
            // return pour interompre la fonction
        }
        
        // (si j'arrive ici ) La partie n'est pas terminée donc joueur à gagné
        
        // sinon on continue 
        //Recupère l'ordre max des joueurs de la partie
        long ordreMax = partiedao.rechercheOrdreMaxJoueurPourPartieId(idPartie);
        
        //joueurEvalue = joueurQuiALaMain
        Joueur joueurEvalue = joueurQuiALaMain;
        
        while(true){// c'est ma boucle qui permer de déterminer le joueur qui 'attrape' la main
            
            //Si joueurEvalue est le dernier joueur alors on evalue le premier
            if(joueurEvalue.getOrdre()>=ordreMax){
               joueurEvalue=joueurdao.rechercheJoueurParPartieIdEtOrdre(idPartie, 0L); 
            }else{
                joueurEvalue = joueurdao.rechercheJoueurParPartieIdEtOrdre(idPartie,joueurEvalue.getOrdre()+1);
            }
            
            //Si tous les joueurs non éliminés étaient en sommeil profond ( et qu'on l'a juste reveillé)
            if (joueurEvalue.getId()==joueurQuiALaMain.getId())
                return;
            // si joueur évalué en sommeil profond alors son état passe à pas la main
            
            if (joueurEvalue.getEtatJoueur()==Joueur.EtatJoueur.SOMMEIL_PROFOND) {
                joueurEvalue.setEtatJoueur(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
                joueurdao.modifier(joueurEvalue);
            }else{
                // N'était pas en sommeil profon
                
                // SI joueurEvalue à pas la main ? Alors c'est lui qui prend la main 
                
                if(joueurEvalue.getEtatJoueur()==Joueur.EtatJoueur.N_A_PAS_LA_MAIN)
                   joueurQuiALaMain.setEtatJoueur(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
                   joueurdao.modifier(joueurQuiALaMain);
                   
                   joueurEvalue.setEtatJoueur(Joueur.EtatJoueur.A_LA_MAIN);
                   joueurdao.modifier(joueurEvalue);
                   
                   return;
            }
        }
        
   }
   
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
