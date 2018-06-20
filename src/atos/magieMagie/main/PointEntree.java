/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.main;

import atos.magieMagie.Dao.JoueurDAO;
import atos.magieMagie.Dao.PartieDAO;
import atos.magieMagie.Entity.Partie;
import atos.magieMagie.service.CarteService;
import atos.magieMagie.service.JoueurService;
import atos.magieMagie.service.PartieService;
import java.awt.PointerInfo;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Administrateur
 */
public class PointEntree {
    
    private PartieService partieService = new PartieService();
    private JoueurService joueurService = new  JoueurService();
    private CarteService carteService = new  CarteService();
    
    // fonction qui s'occupe de lancer pls sos ecrans
    public void menuPrincipal(){
        
        Scanner scan = new Scanner(System.in);
        
        String choix;
        do {            
            System.out.println("Menu principal");
            System.out.println("-------------");
            System.out.println("1. Lister les partie non démarrées");
            System.out.println("2. Créer partie");
            System.out.println("3. Rejoindre partie");
            System.out.println("4. Démarrer partie");
            System.out.println("Q. Quitter");
            System.out.println("Votre choix :");
            
            choix = scan.nextLine();
            switch(choix){
                case "1":
                    List<Partie> partie = partieService.listerPartiesNonDemarrees();
                    System.out.println(partie);
                    break;

                case "2":
                    System.out.println("Entrez le nom d ela partie");
                    String nomPartie = scan.nextLine();
                    partieService.creerNouvellePartie(nomPartie);
                    break;

                case "3":
                    System.out.println("Entrez votre pseudo");
                    String pseudo = scan.nextLine();
                    System.out.println("Entrez votre avatar");
                    String avatar = scan.nextLine();
                    System.out.println("Quelle partie voulez-vous rejoindre");
                    Long id = scan.nextLong();
                    joueurService.rejoindrePartie(pseudo, avatar,id);
                    break;

                case "4":
                    System.out.println("Voulez-vous démarrer une partie");
                    partieService.demarrerPartie(0);
                    break;

                case "Q":
                    
                    break;

                default: System.out.println("Choix inconnu");
                    break;
            }
    }while(!choix.equals("Q"));
    }
    
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
       // si la fonction n'est pas static nous sommes obligé de l'appeler à l'interieur d'un objet
       PointEntree m = new PointEntree();
       
       m.menuPrincipal();
    }
    
    
    
    
    
    
    
    
    
    
    // -------------cours ------------------------
//    public static void main(String[] args) {
//
//        System.out.print("Votre choix :");
//        
//        // system.in lire les entrées
//        // objet scanner peut etre utilisé pls fois de suite, pas besoin de faire un new à chaque fois
//        Scanner s = new Scanner(System.in);
//        
//        //lire du texte ( reccup tout ce que l'user tape au clavier et le renvoie)
//        //s.nextLine();
//        
//        String choix = s.nextLine();
//        System.out.println("Vous avez choisi" + choix);
//    }
//    
    
}
